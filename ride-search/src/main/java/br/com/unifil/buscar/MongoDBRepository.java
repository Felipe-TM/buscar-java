package br.com.unifil.buscar;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

@Repository(value = "MongoDBRepository")
public class MongoDBRepository implements RideSearchRepository {

	private MongoCollection<Document> mongoCollection;

	@Autowired
	public MongoDBRepository(Config config) {
		this.mongoCollection = config.mongoCollection();
	}

	@Override
	public List<Document> searchRide(SearchQuery query) {
		Bson queryOrigin = Filters.nearSphere("origin", new Point(new Position(query.getOrigin())),
				query.getSearchRadiusOrigin(), 0.0);
		Bson queryDestination = Filters.nearSphere("destination", new Point(new Position(query.getDestination())),
				query.getSearchRadiusDestination(), 0.0);
		
		List<Document> result = new ArrayList<>();
		
		switch (query.getFilter()) {
		case ORIGIN_ONLY:{
			mongoCollection.find(queryOrigin).forEach(doc -> result.add(doc));
			return result;
		}
			
		case DESTINATION_ONLY:{
			mongoCollection.find(queryDestination).forEach(doc -> result.add(doc));
			return result;
		}
			
		case BOTH:{
			mongoCollection.find(queryOrigin).filter(queryDestination).forEach(doc -> result.add(doc));
			return result;
		}			
		default:
			throw new IllegalArgumentException("Unexpected Enum value:" + query.getFilter());
		}
		
		
	}
}
