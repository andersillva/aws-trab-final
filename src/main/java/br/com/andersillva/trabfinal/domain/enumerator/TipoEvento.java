package br.com.andersillva.trabfinal.domain.enumerator;

import java.util.HashMap;
import java.util.Map;

import br.com.andersillva.trabfinal.domain.service.exception.ValorForaDominioException;

public enum TipoEvento {

	INICIO("inicio"),
	GOL("gol"),
	PENALTI("penalti"),
	LANCE_PERIGOSO("lance-perigoso"),
	LANCE_NORMAL("lance-normal"),
	INTERVALO("intervalo"),
	ACRESCIMO("acrescimo"),
	SUBSTITUICAO("substituicao"),
	ADVERTENCIA("advertencia"),
	EXPULSAO("expulsao"),
	REVISAO_VAR("revisao-var"),
	PAUSA("pausa"),
	FIM("fim");

    private String tipoEvento;

	TipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    private static final Map<String, TipoEvento> lookup = new HashMap<>();

    static {
        for(TipoEvento tipoEvento : TipoEvento.values()) {
            lookup.put(tipoEvento.getTipoEvento(), tipoEvento);
        }
    }

    public static TipoEvento getByValue(String tipoEvento) {
    	TipoEvento te = lookup.get(tipoEvento);
    	if (te == null)
    		throw new ValorForaDominioException();
        return te;
    }

}
