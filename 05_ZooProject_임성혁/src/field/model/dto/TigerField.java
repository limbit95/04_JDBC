package field.model.dto;

import java.util.List;
import animal.model.dto.Tiger;

public class TigerField {

	private String type;
	private int aceptNumber;
	private List<Tiger> tigerList;
	
	public TigerField() {}

	public TigerField(String type, int aceptNumber, List<Tiger> tigerList) {
		super();
		this.type = type;
		this.aceptNumber = aceptNumber;
		this.tigerList = tigerList;
	}

	@Override
	public String toString() {
		return "TigerField [type=" + type + ", aceptNumber=" + aceptNumber + ", tigerList=" + tigerList + "]";
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAceptNumber() {
		return aceptNumber;
	}
	public void setAceptNumber(int aceptNumber) {
		this.aceptNumber = aceptNumber;
	}
	public List<Tiger> getTigerList() {
		return tigerList;
	}
	public void setTigerList(List<Tiger> tigerList) {
		this.tigerList = tigerList;
	}
	
}