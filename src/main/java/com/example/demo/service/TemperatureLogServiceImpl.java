@Service
public class TemperatureLogServiceImpl implements TemperatureLogService {

    private final TemperatureSensorLogRepository repo;

    public TemperatureLogServiceImpl(TemperatureSensorLogRepository repo) {
        this.repo = repo;
    }

    public TemperatureSensorLog recordLog(TemperatureSensorLog log) { return repo.save(log); }
    public List<TemperatureSensorLog> getLogsByShipment(Long id) { return repo.findByShipmentId(id); }
    public TemperatureSensorLog getLogById(Long id) { return repo.findById(id).orElse(null); }
    public List<TemperatureSensorLog> getAllLogs() { return repo.findAll(); }
}
