package br.com.unifil.buscar.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import br.com.unifil.buscar.config.Config;
import br.com.unifil.buscar.dto.EmailVerification;
import br.com.unifil.buscar.dto.EmailVerificationRecord;

@Repository(value = "RedisRepository")
public class RedisRepository implements VerificationRepository {

	private RedisTemplate<String, String> template;

	@Autowired
	public RedisRepository(Config config) {
		this.template = config.redisTemplate();
	}

	@Override
	public Optional<EmailVerificationRecord> getByUsername(String username) {

		EmailVerification fromJson = new Gson().fromJson(template.opsForValue().get(username), EmailVerification.class);
		
		if (fromJson == null) return Optional.ofNullable(null);

		return Optional.ofNullable(new EmailVerificationRecord(fromJson.getUsername(), fromJson.getEmail(),
				fromJson.getVerificationCode()));
	}

	@Override
	public void save(EmailVerificationRecord record) {
		template.opsForValue().set(record.verificationCode(), new Gson().toJson(record));
	}

	@Override
	public boolean isDuplicatedRequest(String username) {
		return getByUsername(username).isPresent();
	}

	@Override
	public void delete(String verificationCode) {
		template.opsForValue().getAndDelete(verificationCode);
	}

	@Override
	public Optional<EmailVerificationRecord> getByVerificationCode(String verificationCode) {
		
		EmailVerification fromJson = new Gson().fromJson(template.opsForValue().get(verificationCode), EmailVerification.class);
		
		if (fromJson == null) return Optional.ofNullable(null);
		
		return Optional.ofNullable(new EmailVerificationRecord(fromJson.getUsername(), fromJson.getEmail(),
				fromJson.getVerificationCode()));
		
	}

}
