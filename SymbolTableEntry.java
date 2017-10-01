public class SymbolTableEntry{
	private String name;
	private String type;
	private String constructs;
	
	public SymbolTableEntry(){
		name="";
		type="";
		constructs="";
	}

	public SymbolTableEntry(String name,String type,String constructs){
		this.name=name;
		this.type=type;
		this.constructs=constructs;
	}

	@Override
	public String toString() {
		return "SymbolTableEntry [name=" + name + ", type=" + type + ", constructs=" + constructs + "]";
	}


	public String getId() {
		return name;
	}

	public String getType() {
		return type;
	}
	
	public String getconstructs() {
		return constructs;
	}
	
}
