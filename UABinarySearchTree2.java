
public class UABinarySearchTree2 {
	
	public void print() {
		
	}

	public class UAStudent {
		private int id;
		private String firstName;
		private String lastName;
		
		public UAStudent(int id, String firstName, String lastName ) {
			this.id = id;
			this.firstName = firstName; 
			this.lastName = lastName;
			}
			
			public String getFirstName() {
				return firstName;
			}
	
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
	
			public String getLastName() {
				return lastName;
			}
	
			public void setLastName(String lastName) {
				this.lastName = lastName;
			}
	
			public int getId() {
				return id;
			}
	
			public void setId(int id) {
				this.id = id;
			}
		}
}
