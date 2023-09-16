package common.master;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.context.annotation.Configuration;

public class RequestSerializer implements Serializer<ServiceRequestMaster_DTO> {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public byte[] serialize(String topic, ServiceRequestMaster_DTO data) {
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {

			return null;
		}
	}

	@Override
	public void close() {
	}

}
