package genericEvent;

import java.util.List;
import java.util.Map;

public class GenericEvent {

	@SuppressWarnings("unchecked")
	public static Object getEventMessage(Object input) {

		if (input instanceof java.util.LinkedHashMap) {
			if (((Map<?, ?>) input).containsKey("Records")) {
				List<Map<Object, Object>> temp = (List<Map<Object, Object>>) ((Map<?, ?>)input).get("Records");
				String eventSource = null;

				if (temp.get(0).get("eventSource") != null) {
					eventSource = temp.get(0).get("eventSource").toString().substring(4);
				} else if (temp.get(0).get("EventSource") != null) {
					eventSource = temp.get(0).get("EventSource").toString().substring(4);
				}

				switch (eventSource) {
				case "sqs":
					return temp.get(0).get("body");
				case "sns":
					return ((Map<Object, Object>) temp.get(0).get("Sns")).get("Message");
				case "s3":
					return temp.get(0).get("s3");
				}
			}
		}

		return input;
	}
}
