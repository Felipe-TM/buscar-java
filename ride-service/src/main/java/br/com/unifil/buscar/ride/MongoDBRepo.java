package br.com.unifil.buscar.ride;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;

import br.com.unifil.buscar.Config;

@Repository(value = "MongoDBRepo")
public class MongoDBRepo implements RideRepository {

	private MongoCollection<Ride> collection;

	@Autowired
	public MongoDBRepo(Config config) {
		this.collection = config.mongoCollection();
	}

	@Override
	public void saveRide(Ride ride) {
		ride.setRideId(ObjectId.get().toHexString());
		collection.insertOne(ride);

	}

	@Override
	public Ride getRideById(String rideId) {
		Bson filter = Filters.eq("_id", new ObjectId(rideId));
		return collection.find(filter).first();
	}

	@Override
	public void cancelRide(String rideId) {
		Bson filter = Filters.eq("_id", new ObjectId(rideId));
		Bson updateDoc = Updates.set("rideStatus", RideStatus.CANCELED);
		collection.findOneAndUpdate(filter, updateDoc);

	}

	@Override
	public void updateRide(Ride ride) {
		Bson filter = Filters.eq("_id", new ObjectId(ride.getRideId()));
		collection.findOneAndReplace(filter, ride);

	}

	@Override
	public void updateRide(String rideId, String name, String value) {

		Bson filter = Filters.eq("_id", new ObjectId(rideId));
		Bson updateDoc = Updates.set(name, value);
		collection.findOneAndUpdate(filter, updateDoc);

	}

	@Override
	public void insertIntoArray(String rideId, String name, String value) {
		Bson filter = Filters.eq("_id", new ObjectId(rideId));
		Bson updateDoc = Updates.push(name, value);
		collection.findOneAndUpdate(filter, updateDoc);
	}

	@Override
	public RideRecord getFieldsByName(String rideId, String... name) {
		Bson filter = Filters.eq("_id", new ObjectId(rideId));
		Bson projection = Projections.fields(Projections.include(name), Projections.excludeId());
		return collection.find(filter).projection(projection).first().getAsRecord();
	}


}
