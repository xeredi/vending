package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.Vehiculo;
import xeredi.bus.card.model.VehiculoCriteria;

public interface VehiculoErpMapper {
	List<Vehiculo> selectList(final VehiculoCriteria criteria);

}
