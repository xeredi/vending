package xeredi.bus.card.model.mapper;

import java.util.List;

import xeredi.bus.card.model.ServicioXunta;
import xeredi.bus.card.model.ServicioXuntaCriteria;

public interface ServicioXuntaErpMapper {
	List<ServicioXunta> selectList(final ServicioXuntaCriteria criteria);
}
