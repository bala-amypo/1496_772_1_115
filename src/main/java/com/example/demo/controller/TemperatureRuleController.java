@GetMapping("/product")
@Operation(summary = "Get rule for specific product and date")
public ResponseEntity<TemperatureRule> getRuleForProduct(
        @RequestParam String productType,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    
    TemperatureRule rule = service.getRuleForProduct(productType, date);
    if (rule == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(rule);
}
