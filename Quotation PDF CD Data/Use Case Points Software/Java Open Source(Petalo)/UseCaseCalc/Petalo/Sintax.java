import java.util.Vector;

/*
 * Created on 5/01/2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

/**
 * @author jorge
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Sintax {

	public Sintax() {
		losActores = new VectorDActores();
		losUC = new VectorDUC();
		versiondeR = generadorID = " Desconocido";
	}

	public VectorDActores losActores;
	public VectorDUC losUC;
	String versiondeR, generadorID;

	public VectorDeTokens parea(VectorDeTokens fuente) {
		/**
		 * Funcion que devuelve un subVector con elementos
		 * de parentesis equilibrados, partiendo del hecho
		 * que se llama con el primer parentesis removido
		 */
		VectorDeTokens sublista = new VectorDeTokens();
		Token tleido = null;
		int cantidad_de_parentesis_abiertos = 1;
		int cantidad_de_parentesis_cerrados = 0;
		do {
			tleido = fuente.sacaToken();
			if (tleido.esABREPAREN())
				cantidad_de_parentesis_abiertos++;
			else if (tleido.esCIERRAPAREN())
				cantidad_de_parentesis_cerrados++;
			sublista.ingresaToken(tleido);
		} while (
			cantidad_de_parentesis_cerrados < cantidad_de_parentesis_abiertos
				&& !tleido.esEOF());
		return sublista;
	}

	public VectorDeTokens damehastaAbrirP(VectorDeTokens fuente) {
		VectorDeTokens sublista = new VectorDeTokens();
		Token tleido = null;
		do {
			tleido = fuente.sacaToken();
			if (tleido.esABREPAREN())
				break;
			sublista.ingresaToken(tleido);
		} while (!tleido.esEOF());
		return sublista;
	}

	public void parseEncabezado(VectorDeTokens encabezado) {
		VectorDeTokens petalo = parea(encabezado);
		if (!petalo.sacaToken().cadena.equalsIgnoreCase("object"));
		//TODO Manejar error
		if (!petalo.sacaToken().cadena.equalsIgnoreCase("Petal"));
		//TODO Manejar error
		if (!petalo.sacaToken().cadena.equalsIgnoreCase("version"));
		//TODO Manejar error
		Token version = petalo.sacaToken();
		if (!version.esEntero()); //TODO Manejar error
		versiondeR = version.cadena;
		if (!petalo.sacaToken().cadena.equalsIgnoreCase("_written"));
		//TODO Manejar error
		Token generador = petalo.sacaToken();
		if (!generador.esCadena()); //TODO Manejar error
		else
			generadorID = generador.cadena;
		if (!petalo.sacaToken().cadena.equalsIgnoreCase("charSet"));
		//TODO Manejar error
		Token charSet = petalo.sacaToken();
		if (!charSet.esEntero()); //TODO Manejar error
	}

	public void parseaArchivo(String archivo) {
		Lector leeA = new Lector(archivo);
		Lexico lexA = new Lexico(leeA);
		VectorDeTokens vtokens = lexA.anaLex();
		Token tknleido = vtokens.sacaToken();
		if (!tknleido.esABREPAREN()) {
			ManejaErrores.avientaError(
				"Formato de archivo, deberia empezar con '('  se encontró"
					+ tknleido);
			return;
		}
		//Comienza encabezado
		parseEncabezado(vtokens);
		tknleido = vtokens.sacaToken();
		if (!tknleido.esABREPAREN()) {
			ManejaErrores.avientaError(
				"Formato de archivo, Diseño deberia empezar con '('  se encontró"
					+ tknleido);
			return;
		}
		parseDisen(vtokens);
		//Comienza diseño
		//VectorDeTokens basura=damehastaAbrirP(vtokens);
		if (!vtokens.sacaToken().esEOF())
			ManejaErrores.avientaError(
				"Formato de archivo, contiene más información"
					+ vtokens.toStringLex(vtokens.posact - 1));
		System.out.println(
			"Se analizó un archivo mdl generado por "
				+ this.generadorID
				+ " versión "
				+ this.versiondeR);

	}

	/**
	 * @param vtokens
	 */
	private void parseDisen(VectorDeTokens dtokens) {
		Token estaCargado, esUnidad, quid;
		VectorDeTokens disenio = parea(dtokens),
			attributes,
			defaults,
			root_category,
			root_subsystem,
			root_usecase_package,
			process_structure,
			properties;

		if (!disenio.sacaToken().cadena.equalsIgnoreCase("object"));
		//TODO Manejar error
		if (!disenio.sacaToken().cadena.equalsIgnoreCase("Design"));
		//TODO Manejar error
		if (!disenio.sacaToken().cadena.equalsIgnoreCase("\"Logical View\""));
		//TODO Manejar error
		Token tmp = disenio.sacaToken();
		while (!tmp.esEOF()) {
			if (tmp.cadena.equalsIgnoreCase("quid")) {
				quid = disenio.sacaToken();
				if (!quid.esCadena()); //TODO Manejar error
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("is_unit")) {
				esUnidad = disenio.sacaToken();
				if (!esUnidad.esBooleano()); //TODO Manejar error
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("is_loaded")) {
				estaCargado = disenio.sacaToken();
				if (!estaCargado.esBooleano()); //TODO Manejar error
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("defaults")) {
				tmp = disenio.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				defaults = parea(disenio);
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("attributes")) {
				tmp = disenio.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				attributes = parea(disenio);
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("properties")) {
				tmp = disenio.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				properties = parea(disenio);
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("process_structure")) {
				tmp = disenio.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				process_structure = parea(disenio);
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("root_subsystem")) {
				tmp = disenio.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				root_subsystem = parea(disenio);
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("root_category")) {
				tmp = disenio.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				root_category = parea(disenio);
				tmp = disenio.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("root_usecase_package")) {
				tmp = disenio.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				root_usecase_package = parea(disenio);
				parseUCV(root_usecase_package);
				tmp = disenio.sacaToken();
				continue;
			}

			ManejaErrores.avientaError("No es un dato de Diseño" + tmp);
			tmp = disenio.sacaToken();
		}
	}

	/**
	 * @param disenio
	 */
	private void parseUCV(VectorDeTokens disenio) {
		VectorDeTokens ucv = parea(disenio),
			logical_models,
			logical_presentations,
			properties;
		Token quid, exportcontrol, global, documentation;
		if (!ucv.sacaToken().cadena.equalsIgnoreCase("object"));
		//TODO Manejar error
		if (!ucv.sacaToken().cadena.equalsIgnoreCase("Class_Category"));
		//TODO Manejar error
		if (!ucv.sacaToken().cadena.equalsIgnoreCase("\"Use Case View\""));
		//TODO Manejar error
		Token tmp = ucv.sacaToken();
		while (!tmp.esEOF()) {
			if (tmp.cadena.equalsIgnoreCase("quid")) {
				quid = ucv.sacaToken();
				if (!quid.esCadena()); //TODO Manejar error
				tmp = ucv.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("exportControl")) {
				exportcontrol = ucv.sacaToken();
				if (!exportcontrol.esCadena()); //TODO Manejar error
				tmp = ucv.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("global")) {
				exportcontrol = ucv.sacaToken();
				if (!exportcontrol.esBooleano()); //TODO Manejar error
				tmp = ucv.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("documentation")) {
				documentation = ucv.sacaToken();
				if (!documentation.esCadena() && !documentation.esCadena());
				//TODO Manejar error
				tmp = ucv.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("logical_models")) {
				tmp = ucv.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				logical_models = parea(ucv);
				parseaLogMod(logical_models);
				tmp = ucv.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("logical_presentations")) {
				tmp = ucv.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				logical_presentations = parea(ucv);
				parseaLogPres(logical_presentations);
				tmp = ucv.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("properties")) {
				tmp = ucv.sacaToken();
				if (!tmp.esABREPAREN()); //TODO Manejar error
				properties = parea(ucv);
				tmp = ucv.sacaToken();
				continue;
			}

			ManejaErrores.avientaError("No es un dato de UCV" + tmp);
			tmp = ucv.sacaToken();

		}

	}

	/**
	 * @param logical_models
	 */
	private void parseaLogMod(VectorDeTokens logical_models) {
		VectorDeTokens lm = parea(logical_models);
		if (!lm.sacaToken().cadena.equalsIgnoreCase("list"));
		//TODO Manejar error
		if (!lm.sacaToken().cadena.equalsIgnoreCase("unit_reference_list"));
		//TODO Manejar error
		Token tmp = lm.sacaToken();
		while (!tmp.esEOF()) {
			if (!tmp.esABREPAREN()); //TODO Manejar error
			tmp = lm.sacaToken();
			if (!tmp.cadena.equalsIgnoreCase("OBJECT")); //TODO Manejar error
			tmp = lm.sacaToken();
			if (tmp.cadena.equalsIgnoreCase("class_category")) {
				/***
				 * Si se usa RUP aui estarían los Bussines Use Case Model
				 * y el simpe Use Case Model
				 */
				tmp = lm.sacaToken();
				if (tmp
					.cadena
					.equalsIgnoreCase("\"Business Use-Case Model\"")) {
					/**
					 * Como el BUCM no nos interesa por el momento lo vamos a olvidar
					 */
					parea(lm);
				} else {
					/**
					 * se trata de un paquete
					 */
					parseUCV(parea(lm));
				}
				tmp = lm.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("Class")) {
				losActores.agregaActor(parea(lm));
				tmp = lm.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("UseCase")) {
				losUC.agregaUC(parseUC(parea(lm)));
				tmp = lm.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("Association")) {
				parea(lm);
				tmp = lm.sacaToken();
				continue;
			}

		}
	}

	/**
		 * @param tokens
		 * @return
		 */
	private CasoDUso parseUC(VectorDeTokens tokens) {
		CasoDUso elcaso;
		String nombre = tokens.sacaToken().cadena;
		String quid = "";
		String doc = "";
		int transacciones = 0;
		Vector transV=new Vector();

		Token tmp = tokens.sacaToken();
		while (!tmp.esEOF()) {

			if (tmp.cadena.equalsIgnoreCase("quid")) {
				quid = tmp.cadena;
				tmp = tokens.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("documentation")) {
				if (!tmp.esCadena() && !tmp.esCadena());
				doc = tmp.cadena;
				tmp = tokens.sacaToken();
				continue;
			}
			if (tmp.cadena.equalsIgnoreCase("logical_models")) {
				if (!tokens.sacaToken().esABREPAREN()); //TODO Manejar error
				if (!tokens.sacaToken().cadena.equalsIgnoreCase("list"));//TODO Manejar error
				if (!tokens.sacaToken().cadena.equalsIgnoreCase("unit_reference_list"));//TODO Manejar error
				VectorDeTokens logMod = parea(tokens);
				Token tmp2=logMod.sacaToken();
				while(!tmp2.esEOF()){
					
					if(tmp2.cadena.equalsIgnoreCase("object")){
						tmp2=logMod.sacaToken(); 
					    if(tmp2.cadena.equalsIgnoreCase("Message")){
							tmp2=logMod.sacaToken();
							//Se trata del nombre una transacción en algun escenario del caso de uso
							String nombrTrans=tmp2.cadena.toUpperCase();
							if(transV.indexOf(nombrTrans)<0)
							   transV.add(nombrTrans);
						}
					}
				tmp2 = logMod.sacaToken();
				continue;
				}
			}
			tmp = tokens.sacaToken();
			continue;
		}
		transacciones=transV.size();
		elcaso= new CasoDUso(nombre,doc,transacciones);
		return elcaso;
	}
	private void parseaLogPres(VectorDeTokens logical_presentations) {

	}
	public VectorDUC regresaCasos(){
		return this.losUC;
	}
	public VectorDActores regresaActores(){
			return this.losActores;
		}
		
	public VectorDActores regresaActoresU(){
				return this.losActores.regresaAU();
			}

	public static void main(String[] args) {
		Sintax m = new Sintax();
		m.parseaArchivo(args[0]);
		System.out.println("Se reconocieron los siguientes casos"+m.regresaCasos());
		System.out.println("Se reconocieron los siguientes Actores"+m.regresaActores());

	}
}
