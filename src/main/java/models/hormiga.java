package models;

/**
 *
 * @author Cristian Jesús Cruz Mendívil ID:267652
 */
public class hormiga {
    String name;
    int id;
    String photo;
    String descrip;
   
    public hormiga(int id, String name, String photo, String descrip){
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.descrip = descrip;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPhoto(){
        return photo;
    }
    
     public String getDescrip(){
        return descrip;
    }
    
}
