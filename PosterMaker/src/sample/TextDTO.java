package sample;

import java.io.Serializable;

public class TextDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private ResultDTO[] result;

	public ResultDTO[] getResult() {
		return result;
	}

	public void setResult(ResultDTO[] result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "[result = " + result + "]";
	}
}
