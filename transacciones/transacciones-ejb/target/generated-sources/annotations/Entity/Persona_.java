package Entity;

import Entity.Hijo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-30T18:27:45")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, Integer> idp;
    public static volatile SingularAttribute<Persona, Hijo> hijo;
    public static volatile SingularAttribute<Persona, Integer> documento;
    public static volatile SingularAttribute<Persona, String> nombre;

}