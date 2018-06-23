package Services;

import model.Address;
import model.LibraryMember;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajkumar on 6/30/2015.
 */
public class UserService {

	DatabaseConnection dbCon=new DatabaseConnection();
	Connection con=dbCon.getDatabaseConnection();

	LibraryMember member;
	Address address;

	public LibraryMember searchMember(String memberId) {
		member=new LibraryMember();

		String query="SELECT * FROM members,address where members.memberid =? And address.id=members.id";
		try {
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1, memberId);
			ResultSet resultSet=pstm.executeQuery();
			while (resultSet.next()){

				member.setMemberId(resultSet.getString("memberid"));
				member.setFirstName(resultSet.getString("firstName"));
				member.setLastName(resultSet.getString("lastName"));
				member.setPhone(resultSet.getString("phone"));

				address=new Address();
				address.setStreet(resultSet.getString("street"));
				address.setCity(resultSet.getString("city"));
				address.setState(resultSet.getString("state"));
				address.setZip(resultSet.getString("zip"));

				member.setAddress(address);
			}
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

    public boolean addUser(LibraryMember libraryMember){

		address = libraryMember.getAddress();

		String query= "INSERT into address(street,city,state,zip) VALUES (?,?,?,?)";

		try {
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1,address.getStreet());
			pstm.setString(2,address.getCity());
			pstm.setString(3, address.getState());
			pstm.setString(4, address.getZip());
//			pstm.setString(5, libraryMember.getMemberId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			return false;
		}


		query = "INSERT INTO members(firstName,lastName,phone,memberId) VALUES(?,?,?,?) WHERE"+
					"members.addressid=address.id";
		try {
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1,libraryMember.getFirstName());
			pstm.setString(2,libraryMember.getLastName());
			pstm.setString(3,libraryMember.getPhone());
			pstm.setString(4, libraryMember.getMemberId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			return false;
		}

		return true;
    }

	public boolean updateUser(LibraryMember member){
		String query="UPDATE libraryMember SET firstName=?,lastName=?,phone=? WHERE " +
				"memberId=?";
		try {
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1,member.getFirstName());
			pstm.setString(2,member.getLastName());
			pstm.setString(3, member.getPhone());
			pstm.setString(4, member.getMemberId());
			pstm.executeUpdate();
		}catch (SQLException e){
			System.out.println("Exception raised.");
			System.out.println(e.getMessage());
			return false;
		}

		address=member.getAddress();
		query = "UPDATE address SET street=?,city=?,state=?,zip=? WHERE memberId=?";
		try {
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1,address.getStreet());
			pstm.setString(2,address.getCity());
			pstm.setString(3,address.getState());
			pstm.setString(4, address.getZip());
			pstm.setString(5, member.getMemberId());
			pstm.executeUpdate();
		}catch (SQLException e){
			System.out.println("Exception raised.");
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public List<LibraryMember> getAllMembers() {
		List<LibraryMember> members=new ArrayList<>();
		String query = "SELECT * FROM members";

		try {
			PreparedStatement pstm=con.prepareStatement(query);
			ResultSet resultSet=pstm.executeQuery();
			while (resultSet.next()){
				member= new LibraryMember();
				member.setMemberId(resultSet.getString("memberId"));
				member.setFirstName(resultSet.getString("firstName"));
				member.setLastName(resultSet.getString("lastName"));
				member.setPhone(resultSet.getString("phone"));
				members.add(member);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return members;
	}
}
