package main;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public interface AnyData extends Serializable {
    int getNet();
}
