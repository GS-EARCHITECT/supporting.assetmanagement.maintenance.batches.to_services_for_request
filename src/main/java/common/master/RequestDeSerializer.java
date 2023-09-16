package common.master;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

public class RequestDeSerializer implements Deserializer<ServiceRequestMaster_DTO> 
{
    
	 private ObjectMapper objectMapper = new ObjectMapper();

	    @Override
	    public void configure(Map<String, ?> configs, boolean isKey) {
	    }

	    @Override
	    public ServiceRequestMaster_DTO deserialize(String topic, byte[] data) {
	        try {
	            return objectMapper.readValue(new String(data, "UTF-8"), ServiceRequestMaster_DTO.class);
	        } catch (Exception e) {	           
	            return null;
	        }
	    }

	    @Override
	    public void close() {
	    }
}