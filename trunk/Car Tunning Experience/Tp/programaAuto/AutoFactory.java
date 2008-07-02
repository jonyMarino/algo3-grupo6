package programaAuto;

import nu.xom.Element;

import excepciones.IncorrectPartForUbicationException;
import excepciones.UbicationUnkownException;

public interface AutoFactory {
	Auto crear(CadenaDeFabricas fabrica,Element elemento)throws IncorrectPartForUbicationException, UbicationUnkownException;
}
