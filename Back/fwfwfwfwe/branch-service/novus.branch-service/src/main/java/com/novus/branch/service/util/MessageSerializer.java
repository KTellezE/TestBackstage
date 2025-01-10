package com.novus.branch.service.util;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.novus.branch.commons.request.MessageDto;

/**
 * Message Serializer class
 * 
 * @author username@novus.com
 */
public class MessageSerializer extends JsonSerializer<MessageDto>
{

}
