package br.com.unifil.buscar;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.unifil.buscar.ride.Ride;

@Configuration
public class Config {

	@Value("${spring.data.mongodb.uri}")
	private String mongoURI;
	@Value("${spring.data.mongodb.database}")
	private String mongoDatabse;
	@Value("${spring.data.mongodb.collection}")
	private String mongoCollection;
	
	@Bean
	public CodecProvider pojoCodecProvider() {
		
		ClassModel<Ride> ride = ClassModel.builder(Ride.class).build();
		
		return PojoCodecProvider.builder().register(ride).build();
	}

	@Bean
	public CodecRegistry pojoCodecRegistry() {
		return fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider()));
	}

	@Bean
	public MongoClient mongoClient() {

		ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(mongoURI)).serverApi(serverApi).build();
		MongoClient mongoClient = MongoClients.create(settings);

		return mongoClient;
	}
	
	@Bean
	public MongoDatabase mongoDatabase() {
		return mongoClient().getDatabase(mongoDatabse).withCodecRegistry(pojoCodecRegistry());
	}
	
	@Bean
	public MongoCollection<Ride> mongoCollection(){
		return mongoDatabase().getCollection(mongoCollection, Ride.class);
	}
}
