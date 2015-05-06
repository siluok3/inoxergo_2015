package engine;

import mybeans.User;

import java.util.Vector;


/**
 *  This class is used  for handling queries according users in connection with the db.
 * <p>
 *  The tables used in the db are users, usergroups.
 *
 * @author  freelancing.gr
 * @see     org.apache.log4j.Logger
 * @see     DBHandler
 * @see     java.sql.ResultSet
 * @see     Class
 */
public class UserManagement {




          org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("UserManagement.class");


            java.sql.ResultSet rs = null;

            public UserManagement() {}





    /**
    * Checks if a user trying to log in has a right email and password.
    * <p>
    * Searches a user with the username given and returns the password of this user.
    *  If the password given matches the password found in the db, the user is allowed to log in.
    *
    * @param email     the user e-mail (username)
    * @param password  the user password
    * @return          <code>true</code> if the user with the username given has a matching password to the db with the one given;
     *                 <code>false</code> otherwise.
    * @throws Exception    if an error occurs in code
    */
     public boolean AuthorizeUser(String email, String password) throws Exception {
        final String methodsig = "UserManagement.AuthorizeUser()";
        cat.info("MethodStart:" + methodsig);
        DBHandler dbHandler = new DBHandler(cat);
        dbHandler.setQueryString("SELECT password FROM users WHERE email='" + email + "'");

        rs = dbHandler.lookup();
        while (rs.next()) {
            if (rs.getString(1).equalsIgnoreCase(password)) {
                return true;
            }
        }
        dbHandler.close();
        cat.info("MethodEnd:" + methodsig);
        return false;
    }


    /**
       * Checks if the user is an administrator
       *
       * @param email     the user e-mail
       * @return          <code>true</code> if the user is an administrator;
        *                 <code>false</code> otherwise.
       * @throws Exception    if an error occurs in code
       */
          public boolean checkAdmin(String email) throws Exception {
           final String methodsig = "UserManagement.checkAdmin()";
           cat.info("MethodStart:" + methodsig);
           DBHandler dbHandler = new DBHandler(cat);
           dbHandler.setQueryString("SELECT group_id FROM users WHERE email='" + email + "'");
           rs = dbHandler.lookup();

           int usercatid = 0;
           while (rs.next()) {
               usercatid = rs.getInt(1);
           }
             boolean check = false;
            if(usercatid==1)
            { check=true;}

           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return check;
       }



    /**
    * Checks if the user is a buyer
    *
    * @param email     the user e-mail
    * @return          <code>true</code> if the user is a buyer;
     *                 <code>false</code> otherwise.
    * @throws Exception    if an error occurs in code
    */
     public boolean checkUser(String email) throws Exception {
        final String methodsig = "UserManagement.checkUser()";
        cat.info("MethodStart:" + methodsig);
        DBHandler dbHandler = new DBHandler(cat);
        dbHandler.setQueryString("SELECT group_id FROM users WHERE email='" + email + "'");

        rs = dbHandler.lookup();
         int usercatid = 0;
        while (rs.next()) {
            usercatid = rs.getInt(1);
        }
          boolean check = false;
         if(usercatid==2)
         { check=true;}
        dbHandler.close();
        cat.info("MethodEnd:" + methodsig);
        return check;
    }


   /**
    * Gets the user e-mail by the user id
    *
    * @param email     the user e-mail (username)
    * @return          <code>true</code> if the user is an administrator;
     *                 <code>false</code> otherwise.
    * @throws Exception    if an error occurs in code
    */
   public int getuserid(String email) throws Exception {
        final String methodsig = "UserManagement.getuserid()";
        cat.info("MethodStart:" + methodsig);
        DBHandler dbHandler = new DBHandler(cat);
        dbHandler.setQueryString("SELECT user_id  FROM users WHERE email='" + email + "'");

        rs = dbHandler.lookup();

        int userid = 0;

        while (rs.next()) {
            userid=(rs.getInt(1));
        }
        dbHandler.close();
        cat.info("MethodEnd:" + methodsig);
        return userid;
    }




    /**
    * Gets a user entry from table users by the user id
    *
    * @param userid     the user id
    * @return           a user (User)
    * @throws Exception    if an error occurs in code
    * @see              User
    */
    public User getUser(int userid) throws Exception {
           final String methodsig = "UserManagement.getUser()";
           cat.info("MethodStart:" + methodsig);

           DBHandler dbHandler = new DBHandler(cat);
           dbHandler.setQueryString("SELECT user_id, group_id, firstname, surname, email, address, city, country," +
                   " postcode, phone, mobile, password, user_details,foto  FROM users WHERE user_id=" + userid);
           rs = dbHandler.lookup();
           User user = new User();
           while (rs.next()) {

               user.setUser_id(rs.getInt(1));
               user.setGroup_id(rs.getInt(2));
               user.setFirstname(rs.getString(3));
               user.setSurname(rs.getString(4));
               user.setEmail(rs.getString(5));
               user.setAddress(rs.getString(6));
               user.setCity(rs.getString(7));
               user.setCountry(rs.getString(8));
               user.setPostcode(rs.getString(9));
               user.setPhone(rs.getString(10));
               user.setMobile(rs.getString(11));
               user.setPassword(rs.getString(12));
               user.setUser_details(rs.getString(13));
               user.setFoto(rs.getString(14));

           }
           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return user;
       }


    /**
    * Gets all users
    *
    * @return           a vector of users (User)
    * @throws Exception    if an error occurs in code
    * @see              User
    */
    public Vector getAllUsers() throws Exception {
           final String methodsig = "UserManagement.getAllUsers()";
           cat.info("MethodStart:" + methodsig);

           DBHandler dbHandler = new DBHandler(cat);
           dbHandler.setQueryString("SELECT user_id, group_id, firstname, surname, email, address, city, country,"+
                   " postcode, phone, mobile, password, user_details, foto  FROM users WHERE group_id IN (2)");
           rs = dbHandler.lookup();
        Vector uservec = new Vector();

           while (rs.next()) {
               User user = new User();

               user.setUser_id(rs.getInt(1));
               user.setGroup_id(rs.getInt(2));
               user.setFirstname(rs.getString(3));
               user.setSurname(rs.getString(4));
               user.setEmail(rs.getString(5));
               user.setAddress(rs.getString(6));
               user.setCity(rs.getString(7));
               user.setCountry(rs.getString(8));
               user.setPostcode(rs.getString(9));
               user.setPhone(rs.getString(10));
               user.setMobile(rs.getString(11));
               user.setPassword(rs.getString(12));
               user.setUser_details(rs.getString(13));
               user.setFoto(rs.getString(14));
            uservec.addElement(user);
           }
           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return uservec;
       }


    /**
    * Gets all buyers ordered by name
    *
    * @return           a vector of users (User)
    * @throws Exception    if an error occurs in code
    * @see              User
    */
    public Vector getAllUsersByName() throws Exception {
           final String methodsig = "UserManagement.getAllUsersByName()";
           cat.info("MethodStart:" + methodsig);

           DBHandler dbHandler = new DBHandler(cat);
           dbHandler.setQueryString("SELECT user_id, group_id, firstname, surname, email, address, city, country," +
                   " postcode, phone, mobile, password, user_details, foto FROM users WHERE group_id IN (2) ORDER BY firstname,surname ASC");
           rs = dbHandler.lookup();
        Vector uservec = new Vector();

           while (rs.next()) {
               User user = new User();

                user.setUser_id(rs.getInt(1));
               user.setGroup_id(rs.getInt(2));
               user.setFirstname(rs.getString(3));
               user.setSurname(rs.getString(4));
               user.setEmail(rs.getString(5));
               user.setAddress(rs.getString(6));
               user.setCity(rs.getString(7));
               user.setCountry(rs.getString(8));
               user.setPostcode(rs.getString(9));
               user.setPhone(rs.getString(10));
               user.setMobile(rs.getString(11));
               user.setPassword(rs.getString(12));
               user.setUser_details(rs.getString(13));
               user.setFoto(rs.getString(14));
            uservec.addElement(user);
           }
           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return uservec;
       }


    /**
    * Gets all buyers ordered by surname
    *
    * @return           a vector of users (User)
    * @throws Exception    if an error occurs in code
    * @see              User
    */
    public Vector getAllUsersBySurname() throws Exception {
           final String methodsig = "UserManagement.getAllUsersBySurname()";
           cat.info("MethodStart:" + methodsig);

           DBHandler dbHandler = new DBHandler(cat);
           dbHandler.setQueryString("SELECT user_id, group_id, firstname, surname, email, address, city, country," +
                   " postcode, phone, mobile, password, user_details, foto FROM users WHERE group_id IN (2) ORDER BY surname,firstname ASC");
           rs = dbHandler.lookup();
        Vector uservec = new Vector();

           while (rs.next()) {
               User user = new User();

                user.setUser_id(rs.getInt(1));
               user.setGroup_id(rs.getInt(2));
               user.setFirstname(rs.getString(3));
               user.setSurname(rs.getString(4));
               user.setEmail(rs.getString(5));
               user.setAddress(rs.getString(6));
               user.setCity(rs.getString(7));
               user.setCountry(rs.getString(8));
               user.setPostcode(rs.getString(9));
               user.setPhone(rs.getString(10));
               user.setMobile(rs.getString(11));
               user.setPassword(rs.getString(12));
               user.setUser_details(rs.getString(13));
               user.setFoto(rs.getString(14));
            uservec.addElement(user);
           }
           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return uservec;
       }



  /**
    * Gets a user password and a few more user details by the user id
    *
    * @param email     the user e-mail (username)
    * @return          the user password and a few more user details (User)
    * @throws Exception    if an error occurs in code
    * @see             User
    */
 public User getUserPassword(String email) throws Exception {
           final String methodsig = "UserManagement.getUserPassword()";
           cat.info("MethodStart:" + methodsig);

           DBHandler dbHandler = new DBHandler(cat);
           dbHandler.setQueryString("SELECT user_id, group_id, firstname, surname, password FROM users WHERE email='" + email+"'");
           rs = dbHandler.lookup();

           User user = new User();
           while (rs.next()) {
               user.setUser_id(rs.getInt(1));
               user.setGroup_id(rs.getInt(2));
               user.setFirstname(rs.getString(3));
               user.setSurname(rs.getString(4));
               user.setEmail(email);
               user.setPassword(rs.getString(5));

           }
           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return user;
       }


   /**
    * Inserts a new user to the db
    *
    * @param user       the user data (User)
    * @return           the user id if the entry succeeded or else 0
    * @throws Exception    if an error occurs in code
    * @see             User
    */
      public int insertNewUser(User user) throws Exception {
        final String methodsig = "UserManagement.insertNewBuyer()";
        cat.info("MethodStart:" + methodsig);
        DBHandler dbHandler = new DBHandler(cat);
        dbHandler.setQueryString("INSERT INTO users (user_id, group_id, firstname, surname, email, address, city, country," +
                " postcode, phone, mobile, password, user_details, foto) VALUES("
                + user.getUser_id() + ","+ user.getGroup_id() + ",'" + user.getFirstname() + "','" + user.getSurname() +
                "',?,'" + user.getAddress() + "','" + user.getCity()+ "','" + user.getCountry() + "','" + user.getPostcode() +
                "','" + user.getPhone() + "','" + user.getMobile() + "','" + user.getPassword() + "','"
                + user.getUser_details() + "','" + user.getFoto() + "')");
        cat.info("try to insert user " + user.getEmail() + ":");
        dbHandler.executeUpdate(user.getEmail());

        String sql2 = "SELECT user_id FROM users WHERE email='" + user.getEmail() + "'";
        dbHandler.setQueryString(sql2);
        rs = dbHandler.lookup();

        int userid = 0;
        while (rs.next()) {
            userid = rs.getInt(1);
        }
        dbHandler.close();
        cat.info("MethodEnd:" + methodsig);
        return userid;
    }



     /**
    * Updates a user to the db
    *
    * @param user       the user data (User)
    * @return           1 if the entry was updated or else 0
    * @throws Exception    if an error occurs in code
    * @see             User
    */
    public int updateUser(User user) throws Exception {
          final String methodsig = "UserManagement.updateUser()";
          cat.info("MethodStart:" + methodsig);
          DBHandler dbHandler = new DBHandler(cat);
          int reply = 0;
          dbHandler.setQueryString("UPDATE users SET  group_id=" + user.getGroup_id() +
                  ", firstname='" + user.getFirstname() + "', surname='" + user.getSurname() +
                  "',email='" + user.getEmail() + "', address='" + user.getAddress() +
                  "', city='" + user.getCity() + "',country='" + user.getCountry() +
                  "',postcode='" + user.getPostcode() + "',phone='" + user.getPhone() +
                  "',mobile='" + user.getMobile() +
                  "',password='" + user.getPassword() + "',user_details='" + user.getUser_details() +
                  "',foto='" + user.getFoto() +  "' WHERE user_id=? ");
          reply = dbHandler.executeUpdate(String.valueOf(user.getUser_id()));

          dbHandler.close();
          cat.info("MethodEnd:" + methodsig);
          return reply;
      }


    /**
    * Checks if a user with a particular e-mail exists in the db
    *
    * @param email      the user e-mail
    * @return           <code>true</code> if the user with the particular e-mail exists;
     *                  <code>false</code> otherwise.
    * @throws Exception    if an error occurs in code
    * @see             User
    */
        public boolean checkExistedUser(String email) throws Exception {
        final String methodsig = "UserManagement.checkExistedUser()";
        cat.info("MethodStart:" + methodsig);
        DBHandler dbHandler = new DBHandler(cat);
        dbHandler.setQueryString("SELECT * FROM users WHERE email='" + email + "'");
        rs = dbHandler.lookup();

        boolean check = false;
        while (rs.next()) {
            check = true;
        }
        dbHandler.close();
        cat.info("MethodEnd:" + methodsig);
        return check;
    }



    /** Gets user's profile  by username = email
    *
    * @param email      the user e-mail
    * @return           the user
    * @throws Exception    if an error occurs in code
    * @see             User
    */
     public User getUserprofilebyemail(String email) throws Exception {
         final String methodsig = "UserManagement.getUserprofile()";
         cat.info("MethodStart:" + methodsig);

         DBHandler dbHandler = new DBHandler(cat);
         dbHandler.setQueryString("SELECT user_id, group_id, firstname, surname, email, address, city, country," +
                 " postcode, phone, mobile, password, user_details, foto  FROM users WHERE UserName='" + email + "'");
         rs = dbHandler.lookup();
         User user = new User();
         while (rs.next()) {

             user.setUser_id(rs.getInt(1));
               user.setGroup_id(rs.getInt(2));
               user.setFirstname(rs.getString(3));
               user.setSurname(rs.getString(4));
               user.setEmail(rs.getString(5));
               user.setAddress(rs.getString(6));
               user.setCity(rs.getString(7));
               user.setCountry(rs.getString(8));
               user.setPostcode(rs.getString(9));
               user.setPhone(rs.getString(10));
               user.setMobile(rs.getString(11));
               user.setPassword(rs.getString(12));
               user.setUser_details(rs.getString(13));
               user.setFoto(rs.getString(14));
         }
         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return user;
     }

     /** Gets user's profile  by firstname and surname
    *
    * @param str1       either firstname or surname
    * @param str2       either firstname or surname
    * @return           Vector of possible matching users
    * @throws Exception    if an error occurs in code
    * @see            User
    */
     public Vector getUserprofilesbyName(String str1,String str2 ) throws Exception {
         final String methodsig = "UserManagement.getUserprofilesbyName()";
         cat.info("MethodStart:" + methodsig);

         DBHandler dbHandler = new DBHandler(cat);
         dbHandler.setQueryString("SELECT DISTINCT user_id, group_id, firstname, surname, email, address, city, country," +
                 " postcode, phone, mobile, password, user_details, foto FROM users " +
                 "WHERE firstname LIKE '"+str1+"' OR firstname LIKE '"+str2+"' OR surname LIKE '"+str1+"' OR surname LIKE '"+str2+"'  ");
         rs = dbHandler.lookup();
          java.util.Vector v = new java.util.Vector();
         while (rs.next()) {
             User user = new User();
             user.setUser_id(rs.getInt(1));
               user.setGroup_id(rs.getInt(2));
               user.setFirstname(rs.getString(3));
               user.setSurname(rs.getString(4));
               user.setEmail(rs.getString(5));
               user.setAddress(rs.getString(6));
               user.setCity(rs.getString(7));
               user.setCountry(rs.getString(8));
               user.setPostcode(rs.getString(9));
               user.setPhone(rs.getString(10));
               user.setMobile(rs.getString(11));
               user.setPassword(rs.getString(12));
               user.setUser_details(rs.getString(13));
               user.setFoto(rs.getString(14));
             v.add(user);
             }
         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return v;
     }
    


    /** Deletes user by id and email
    *
    * @param id         user id
    * @param email      user email
    * @return           <code>true</code> if the user is deleted;
     *                  <code>false</code> otherwise.
    * @throws Exception    if an error occurs in code
    * @see            User
    */
    public boolean deleteUser(int id, String email)  throws Exception {
         final String methodsig = "UserManagement.deleteUser()";
         cat.info("MethodStart:" + methodsig);

         DBHandler dbHandler = new DBHandler(cat);

         dbHandler.setQueryString( "DELETE  FROM users  WHERE user_id=" + id + " AND email='"+email+ "'");
         boolean check=dbHandler.execute();

         dbHandler.close();
         cat.info("MethodEnd:" + methodsig);
         return check;

     }


    /**Gets the name of a user group by its id
    *
    * @param groupid    user group id
    * @return           the name of the group
    * @throws Exception    if an error occurs in code
    */
    public String getUserGroup(String groupid) throws Exception {

          final String methodsig = "UserManagement.getUserGroup()";
          cat.info("MethodStart:" + methodsig);
          DBHandler dbHandler = new DBHandler(cat);

           String groupname=null;

        dbHandler.setQueryString("SELECT `group_name` FROM usergroups WHERE group_id="+groupid);
        rs = dbHandler.lookup();

          while (rs.next()) {
          groupname=rs.getString(1);
          }

       dbHandler.close();
      cat.info("MethodEnd:" + methodsig);
       return groupname;
      }


    /**Gets all e-mail addresses from users
     *@return              vector of all emails
    * @throws Exception    if an error occurs in code
    */
       public Vector getAllExistingEmails() throws Exception {
           final String methodsig = "UserManagement.getAllExistingEmails()";
           cat.info("MethodStart:" + methodsig);
           java.util.Vector vec = new java.util.Vector();

           DBHandler dbHandler = new DBHandler(cat);

           dbHandler.setQueryString("SELECT email FROM users");
           rs = dbHandler.lookup();


           while (rs.next()) {

              String email=rs.getString(1);
               vec.addElement(email);
           }
           dbHandler.close();
           cat.info("MethodEnd:" + methodsig);
           return vec;
       }


    

}