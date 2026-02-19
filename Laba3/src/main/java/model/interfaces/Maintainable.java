package model.interfaces;

import java.time.LocalDateTime;

public interface Maintainable {
    void performMaintenance(String description);
    boolean needsMaintenance();
    LocalDateTime getNextMaintenanceDate();
    String getMaintenanceReport();
}