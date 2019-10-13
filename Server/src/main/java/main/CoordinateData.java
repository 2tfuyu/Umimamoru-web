package main;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface CoordinateData extends AnyData {
    double getLatitude();

    double getLongitude();
}
