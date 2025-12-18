@RestController
@RequestMapping("/api/logs")
@Tag(name = "Temperature Logs")
public class TemperatureLogController {

    private final TemperatureLogService service;

    public TemperatureLogController(TemperatureLogService service) {
        this.service = service;
    }

    @PostMapping
    public TemperatureSensorLog create(@RequestBody TemperatureSensorLog log) {
        return service.recordLog(log);
    }

    @GetMapping("/shipment/{id}")
    public List<TemperatureSensorLog> byShipment(@PathVariable Long id) {
        return service.getLogsByShipment(id);
    }

    @GetMapping("/{id}")
    public TemperatureSensorLog byId(@PathVariable Long id) {
        return service.getLogById(id);
    }

    @GetMapping
    public List<TemperatureSensorLog> all() {
        return service.getAllLogs();
    }
}
