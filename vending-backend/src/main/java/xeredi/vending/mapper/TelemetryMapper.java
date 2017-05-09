package xeredi.vending.mapper;

import xeredi.vending.json.Telemetry;

public interface TelemetryMapper {
	void insert(final Telemetry telemetry);

	int update(final Telemetry telemetry);
}
