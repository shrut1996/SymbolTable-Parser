import java.util.*;

class SymbolTableEntry{
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

public class SymbolTable {
	
	private ArrayList<SymbolTableEntry> tableEntries;
	private SymbolTable parent;
	private ArrayList<SymbolTable> children;
	
	public SymbolTable() {
		tableEntries=new ArrayList<>();
		children=new ArrayList<>();
	}
	
	
	public void makeParent(SymbolTable parent) {
		this.parent = parent;
	}
	

	public SymbolTableEntry insert(String name,String cons,String type) {
		System.out.println(cons+" : "+type+" "+name);
		for(SymbolTableEntry entry:tableEntries) {
			if(entry.getId().equals(name)) {
				System.out.println("This name Already Exists");
					return null;
			}
		}
		SymbolTableEntry newEntry=new SymbolTableEntry(name,cons,type);
		System.out.println("Entering.");
		return newEntry;
	}


	public SymbolTableEntry lookup(String name,SymbolTable st) {
			for(SymbolTableEntry entry:st.tableEntries) {
			if(entry.getId().equals(name)) {
				System.out.println("Entry Found");
				return entry;
			}
			System.out.println("Not found here.Searching in parent!");
			}
			if(st.parent!=null)
				return lookup(name,st.parent);
		return null;
			
	}


	public SymbolTable enter_scope(SymbolTable st) {
		SymbolTable newScope=new SymbolTable();
		st.children.add(newScope);
		newScope.makeParent(st);
		return newScope;
		
	}


	public SymbolTable exit_scope(SymbolTable st) {
		return parent;
	}
	
	public  static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		SymbolTable symTable = new SymbolTable();
		boolean flag = true;
		while(flag) {	
			System.out.println("Press:" + "\tE : Enter Scope\n" + "\tX : Exit Scope\n" + "\tL : Lookup\n" + "\tS : Symbol Table Entry\n" + "\tQ : Quit");
			String in=s.next();
			switch(in) {
			case "E":
				System.out.println("Entering a new scope");
				try {
					symTable = symTable.enter_scope(symTable);
				} catch (Exception e) {
					System.out.print("Error ! You are now out of the table structure.");
				}
				break;
			case "X":
				System.out.println("Exiting the current scope");			
				try {
					symTable = symTable.exit_scope(symTable);
				} catch (Exception e) {
					System.out.print("Error ! You are now out of the table structure.");
				}
				break;
			case "L":
				System.out.println("Type the name you want to lookup.");
				String name=s.next();
				try {
					SymbolTableEntry t1 = symTable.lookup(name, symTable);
					if (t1 != null)
						System.out.println(t1);
					else
						System.out.println("Not Found");
				} catch (Exception e) {
					System.out.println("Some error found!");
				}
				break;				
			case "S":
				System.out.println("Enter in this order: <name> <construct> <type>");	
				System.out.println("Construct(s) = 'var / 'fun' \nType(s) = 'void' / 'int' / 'bool' / 'double'");
				String name1=s.next();
				String cons=s.next();
				String type=s.next();
				try {
					symTable.tableEntries.add(symTable.insert(name1, cons, type));
					cons.toLowerCase();
					cons.toLowerCase();
					if (cons.equals("fun")) {
						int noOfParameters;
						System.out.println("Enter the number of parameters (0/1/2) : ");
						noOfParameters = s.nextInt();
						if (noOfParameters == 1) {

							System.out.println("Enter parameter 1: <name> <type>");
							String name2 = s.next();
							String type2 = s.next();
							String par = "par";
							symTable.tableEntries.add(symTable.insert(name2, par, type2));
							System.out.println("Parameter added");

						} else if (noOfParameters == 2) {

							String par = "par";
							System.out.println("Enter parameter 1: <name> <type>");
							String namepar1 = s.next();
							String typepar1 = s.next();
							symTable.tableEntries.add(symTable.insert(namepar1, par, typepar1));


							System.out.println("Parameter added");
							System.out.println("Enter parameter 2: <name> <type>");
							String namepar2 = s.next();
							String typepar2 = s.next();
							symTable.tableEntries.add(symTable.insert(namepar2, par, typepar2));
							System.out.println("Parameter added");

						} else
							System.out.println("No parameters");
					} 
				} catch (Exception e) {
					System.out.println("An error has occurred!");
				}
				break;
			 default:
							System.out.println("\nExiting the program.");
							flag = false;
							return;
							
			}	
	
		}
		s.close();
	}

}
