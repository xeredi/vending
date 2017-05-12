package xeredi.vending.json;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Telemetria.
 */
@Data
public final class Telemetry {

	/** The id. */
	@SerializedName("id")
	private Long id;

	private String readerCode;

	/** The fecha. */
	@SerializedName("p_fechahora")
	private Date fecha;

	/** The fecha ultimo volcado. */
	@SerializedName("timestamp_ult_volcado")
	private Date fechaUltimoVolcado;

	/** The fecha lectura. */
	@SerializedName("timestamp_lectura_p")
	private Date fechaLectura;

	/** The version prot. */
	@SerializedName("p_version_prot")
	private String versionProt;

	/** The modelo. */
	@SerializedName("p_modelo")
	private String modelo;

	/** The maquina. */
	@SerializedName("p_id_maquina")
	private String maquina;

	/** The fabricante. */
	@SerializedName("p_fabricante")
	private String fabricante;

	/** The num volcados. */
	@SerializedName("num_volcados")
	private Long numVolcados;

	/** The num lect intentos. */
	@SerializedName("num_lect_intentos_tot")
	private Long numLectIntentos;

	/** The num lect intentos ok. */
	@SerializedName("num_lect_intentos_ok")
	private Long numLectIntentosOk;

	/** The accion id. */
	@SerializedName("id_accion")
	private Long accionId;

	/** The bytes leidos. */
	@SerializedName("bytes_leidos")
	private Long bytesLeidos;

	/** The bytes escritos. */
	@SerializedName("bytes_escritos")
	private Long bytesEscritos;

	/** The estado V 1. */
	@SerializedName("p_estado_v1")
	private String estadoV1;

	/** The estado V 2. */
	@SerializedName("p_estado_v2")
	private String estadoV2;

	/** The estado V 3. */
	@SerializedName("p_estado_v3")
	private String estadoV3;

	/** The estado V 4. */
	@SerializedName("p_estado_v4")
	private String estadoV4;

	/** The estado V 5. */
	@SerializedName("p_estado_v5")
	private String estadoV5;

	/** The estado V 6. */
	@SerializedName("p_estado_v6")
	private String estadoV6;

	/** The estado V 7. */
	@SerializedName("p_estado_v7")
	private String estadoV7;

	/** The estado V 8. */
	@SerializedName("p_estado_v8")
	private String estadoV8;

	/** The estado V 9. */
	@SerializedName("p_estado_v9")
	private String estadoV9;

	/** The estado V 10. */
	@SerializedName("p_estado_v10")
	private String estadoV10;

	/** The estado V 11. */
	@SerializedName("p_estado_v11")
	private String estadoV11;

	/** The estado V 12. */
	@SerializedName("p_estado_v12")
	private String estadoV12;

	/** The estado V 13. */
	@SerializedName("p_estado_v13")
	private String estadoV13;

	/** The estado V 14. */
	@SerializedName("p_estado_v14")
	private String estadoV14;

	/** The estado V 15. */
	@SerializedName("p_estado_v15")
	private String estadoV15;

	/** The estado V 16. */
	@SerializedName("p_estado_v16")
	private String estadoV16;

	/** The tot sal. */
	@SerializedName("p_TOT_SAL")
	private Long totSal;

	/** The tot ent. */
	@SerializedName("p_TOT_ENT")
	private Long totEnt;

	/** The tot caj. */
	@SerializedName("p_TOT_CAJ")
	private Long totCaj;

	/** The tot 5 ap. */
	@SerializedName("p_TOT_5AP")
	private Long tot5ap;

	/** The tot 4 ap. */
	@SerializedName("p_TOT_4AP")
	private Long tot4ap;

	/** The tot 3 ap. */
	@SerializedName("p_TOT_3AP")
	private Long tot3ap;

	/** The tot 2 ap. */
	@SerializedName("p_TOT_2AP")
	private Long tot2ap;

	/** The tot 1 ap. */
	@SerializedName("p_TOT_1AP")
	private Long tot1ap;

	/** The porcent. */
	@SerializedName("p_PORCENT")
	private Long porcent;

	/** The play 10 sal. */
	@SerializedName("p_PLAY10_SAL")
	private Long play10Sal;

	/** The play 10 ent. */
	@SerializedName("p_PLAY10_ENT")
	private Long play10Ent;

	/** The hop 5000. */
	@SerializedName("p_HOP_5000")
	private Long hop5000;

	/** The hop 2000. */
	@SerializedName("p_HOP_2000")
	private Long hop2000;

	/** The hop 1000. */
	@SerializedName("p_HOP_1000")
	private Long hop1000;

	/** The hop 0500. */
	@SerializedName("p_HOP_0500")
	private Long hop0500;

	/** The hop 0200. */
	@SerializedName("p_HOP_0200")
	private Long hop0200;

	/** The hop 0100. */
	@SerializedName("p_HOP_0100")
	private Long hop0100;

	/** The hop 0050. */
	@SerializedName("p_HOP_0050")
	private Long hop0050;

	/** The hop 0020. */
	@SerializedName("p_HOP_0020")
	private Long hop0020;

	/** The hop 0010. */
	@SerializedName("p_HOP_0010")
	private Long hop0010;

	/** The ent 5000. */
	@SerializedName("p_ENT_5000")
	private Long ent5000;

	/** The ent 2000. */
	@SerializedName("p_ENT_2000")
	private Long ent2000;

	/** The ent 1000. */
	@SerializedName("p_ENT_1000")
	private Long ent1000;

	/** The ent 0500. */
	@SerializedName("p_ENT_0500")
	private Long ent0500;

	/** The ent 0200. */
	@SerializedName("p_ENT_0200")
	private Long ent0200;

	/** The ent 0100. */
	@SerializedName("p_ENT_0100")
	private Long ent0100;

	/** The ent 0050. */
	@SerializedName("p_ENT_0050")
	private Long ent0050;

	/** The ent 0020. */
	@SerializedName("p_ENT_0020")
	private Long ent0020;

	/** The ent 0010. */
	@SerializedName("p_ENT_0010")
	private Long ent0010;

	/** The sal 5000. */
	@SerializedName("p_SAL_5000")
	private Long sal5000;

	/** The sal 2000. */
	@SerializedName(value = "p_SAL_2000")
	private Long sal2000;

	/** The sal 1000. */
	@SerializedName("p_SAL_1000")
	private Long sal1000;

	/** The sal 0500. */
	@SerializedName("p_SAL_0500")
	private Long sal0500;

	/** The sal 0200. */
	@SerializedName("p_SAL_0200")
	private Long sal0200;

	/** The sal 0100. */
	@SerializedName("p_SAL_0100")
	private Long sal0100;

	/** The sal 0050. */
	@SerializedName("p_SAL_0050")
	private Long sal0050;

	/** The sal 0020. */
	@SerializedName("p_SAL_0020")
	private Long sal0020;

	/** The sal 0010. */
	@SerializedName("p_SAL_0010")
	private Long sal0010;

	/** The caj 5000. */
	@SerializedName("p_CAJ_5000")
	private Long caj5000;

	/** The caj 2000. */
	@SerializedName("p_CAJ_2000")
	private Long caj2000;

	/** The caj 1000. */
	@SerializedName("p_CAJ_1000")
	private Long caj1000;

	/** The caj 0500. */
	@SerializedName("p_CAJ_0500")
	private Long caj0500;

	/** The caj 0200. */
	@SerializedName("p_CAJ_0200")
	private Long caj0200;

	/** The caj 0100. */
	@SerializedName("p_CAJ_0100")
	private Long caj0100;

	/** The caj 0050. */
	@SerializedName("p_CAJ_0050")
	private Long caj0050;

	/** The caj 0020. */
	@SerializedName("p_CAJ_0020")
	private Long caj0020;

	/** The caj 0010. */
	@SerializedName("p_CAJ_0010")
	private Long caj0010;

	/** The config maquina. */
	@SerializedName("p_config_maquina")
	private String configMaquina;

	/** The raw data. */
	@SerializedName("rawdata")
	private String rawData;
}
