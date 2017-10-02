package sample;

import java.io.Serializable;

public class ResultDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String body;

	private String heading;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	@Override
	public String toString() {
		return "[body = " + body + ", heading = " + heading + "]";
	}
}
