import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	private int id;
	private int user_id;
	private double value;
	private String status;
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getUserId() {return user_id;}
	public void setUserId(int user_id) {this.user_id = user_id;}
	public double getValue() {return value;}
	public String getValueString() {return String.format("$%.2f", value);}
	public void setValue(double value) {this.value = Math.floor(value * 100) / 100;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
}
