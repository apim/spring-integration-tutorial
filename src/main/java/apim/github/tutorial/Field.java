package apim.github.tutorial;

import java.io.Serializable;

public class Field implements Serializable {

	private static final long serialVersionUID = 101L;

	private FieldDescriptor desc;

	private Object value;

	public Field(FieldDescriptor desc, Object value) {
		this.desc = desc;
		this.value = value;
	}

	public FieldDescriptor getDesc() {
		return desc;
	}

	public void setDesc(FieldDescriptor desc) {
		this.desc = desc;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String toString() {
		return desc + "-" + value;
	}

	public enum FieldDescriptor {

		NAME(1), ADDRESS(2), BALANCE(3);

		private int fieldId;

		private FieldDescriptor(int fieldId) {
			this.fieldId = fieldId;
		}

		public int getFieldId() {
			return fieldId;
		}
	}

}
