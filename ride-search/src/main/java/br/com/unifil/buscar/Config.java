package br.com.unifil.buscar;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
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

/**
 * Config is a configuration class, this is where the required dependencies
 * are declared and instantiated.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

@Configuration
public class Config {

	@Value("${spring.data.mongodb.uri}")
	private String mongoURI;
	@Value("${spring.data.mongodb.database}")
	private String mongoDatabse;
	@Value("${spring.data.mongodb.collection}")
	private String mongoCollection;
	
	
	/**
	 * Creates a CodecProvider with default values.
	 * 
	 * @return {@link CodecProvider}
	 * @since 1.0
	 */
	
	@Bean
	public CodecProvider pojoCodecProvider() {
		
		return PojoCodecProvider.builder().automatic(true).build();
	}
	
	
	/**
	 * Creates a CodecRegistry with default values from pojoCodecProvider()
	 * 
	 * @return {@link CodecRegistry}
	 * @since 1.0
	 */

	@Bean
	public CodecRegistry pojoCodecRegistry() {
		return fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider()));
	}
	
	/**
	 * Creates a connection to MongoDB Atlas cluster with
	 * 
	 * @return {@link CodecRegistry}
	 * @since 1.0
	 */

	@Bean
	public MongoClient mongoClient() {

		ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(mongoURI)).serverApi(serverApi).build();
		MongoClient mongoClient = MongoClients.create(settings);

		return mongoClient;
	}
	
	/**
	 * Creates a connection to the MongoDB Atlas cluster.
	 * 
	 * @return {@link CodecRegistry}
	 * @since 1.0
	 */
	
	@Bean
	public MongoDatabase mongoDatabase() {
		return mongoClient().getDatabase(mongoDatabse).withCodecRegistry(pojoCodecRegistry());
	}
	
	@Bean
	public MongoCollection<Document> mongoCollection(){
		return mongoDatabase().getCollection(mongoCollection, Document.class);
	}
}
