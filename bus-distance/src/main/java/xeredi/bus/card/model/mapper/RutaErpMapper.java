package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.RutaCriteria;

public interface RutaErpMapper {
	List<Ruta> selectList(final RutaCriteria criteria);

}
