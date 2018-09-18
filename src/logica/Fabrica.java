/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.Clases.Colaborador;
import logica.Clases.EstadoPropuesta;
import logica.Clases.Proponente;
import logica.Clases.TipoE;
import logica.Clases.TipoRetorno;
import logica.Clases.Usuario;
import logica.Interfaces.IPropCat;
import logica.Controladores.ControladorUsuario;
import logica.Controladores.ControladorPropCat;
import logica.Interfaces.IControladorUsuario;

/**
 *
 * @author Santiago.S
 */
public class Fabrica {

    //SINGLETON
    private static Fabrica instancia;
    boolean datosCargados = false;

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    private Fabrica() {
        this.cargarDatosPropuesta();
        this.cargardatosColaboracion();
    }

    public boolean limpiarBaseDeDatos() {
        IControladorUsuario ICU = this.getIControladorUsuario();
        boolean ok = ICU.limpiarBaseDeDatos();
        return ok;
    }

    public boolean getdatosCargados() {
        return this.datosCargados;
    }

    private void cargarDatosPropuesta() {
        IPropCat ip = this.getControladorPropCat();

        //ip.CargarPropuestas();
    }

    private void cargardatosColaboracion() {
        IPropCat IP = this.getControladorPropCat();
    }

    public IPropCat getControladorPropCat() {
        IPropCat ControladorPC = ControladorPropCat.getInstance();
        return ControladorPC;
    }

    public IControladorUsuario getIControladorUsuario() {
        IControladorUsuario ControladorU = ControladorUsuario.getInstance();
        return ControladorU; //To change body of generated methods, choose Tools | Templates.
    }

    public void cargarDatosdePrueba() {
        IControladorUsuario ICU = this.getIControladorUsuario();
        IPropCat IPC = this.getControladorPropCat();
        String ruta = System.getProperty("user.dir") + "\\fotosdp\\";
//    
//    DBUsuario d=new DBUsuario();
//    d.limpiarBD();
//           

        //-----------------------------------------------------Proponentes------------------------------------//   
        ICU.AgregarUsuarioProponente("hrubino", "Horacio", "Rubino", "horacio.rubino@guambia.com.uy", new GregorianCalendar(1962, 01, 25),
                ruta + "hrubino.jpg", "18 de Julio 1234", "Horacio Rubino Torres nace el 25 de " + "febrero de 1962, es conductor, actor y libretista.  Debuta en "
                + "1982 en carnaval en Los \"Klaper´s\", donde estuvo cuatro" + " años, actuando y libretando. Luego para \"Gaby´s\" (6 años),"
                + "escribió en categoría revistas y humoristas y desde el comienzo y hasta el presente"
                + " en su propio conjunto Momosapiens. ", "https://twitter.com/horaciorubino","");

        ICU.AgregarUsuarioProponente("mbusca", "Martín", "Buscaglia", "Martin.bus@agadu.org.uy", new GregorianCalendar(1972, 05, 14),
                ruta + "mbusca.jpg", "Colonia 4321", "Martín Buscaglia (Montevideo, 1972) es un artista, músico, compositor y productor uruguayo."
                + " Tanto con su banda (“Los Bochamakers”) como en su formato “Hombre orquesta”, o solo con su guitarra, "
                + "ha recorrido el mundo tocando entre otros países en España, Estados Unidos, Inglaterra, Francia, Australia, "
                + "Brasil, Colombia, Argentina, Chile, Paraguay, México y Uruguay. (Actualmente los Bochamakers son Matías Rada,"
                + " Martín Ibarburu, Mateo Moreno, Herman Klang) Paralelamente, tiene proyectos a dúo con el español Kiko Veneno, "
                + "la cubana Yusa, el argentino Lisandro Aristimuño, su compatriota Antolín, y a trío junto a los brasileros Os "
                + "Mulheres Negras.", "http://www.martinbuscaglia.com/","");

        ICU.AgregarUsuarioProponente("hectorg", "Héctor", "Guido", "hector.gui@elgalpon.org.uy", new GregorianCalendar(1954, 00, 07),
                ruta + "hectorg.jpg", "Gral. Flores 5645", "En 1972 ingresó a la Escuela de Arte Dramático del teatro El Galpón. Participó en más de treinta "
                + "obras teatrales y varios largometrajes. Integró el elenco estable de Radioteatro del Sodre, y en 2006 fue asesor "
                + "de su Consejo Directivo. Como actor recibió múltiples reconocimientos: cuatro premios Florencio, premio al mejor actor"
                + " extranjero del Festival de Miami y premio Mejor Actor de Cine 2008. Durante varios períodos fue directivo del teatro"
                + " El Galpón y dirigente de la Sociedad Uruguaya de Actores (SUA); integró también la Federación Uruguaya de Teatros "
                + "Independientes (FUTI). Formó parte del equipo de gestión de la refacción de los teatros La Máscara, Astral y El Galpón, "
                + "y del equipo de gestión en la construcción del teatro De la Candela y de la sala Atahualpa de El Galpón.", "","");

        ICU.AgregarUsuarioProponente("tabarec", "Tabaré", "Cardozo", "tabare.car@agadu.org.uy", new GregorianCalendar(1971, 06, 24),
                ruta + "tabarec.jpg", "Santiago Rivas 1212", "Tabaré Cardozo (Montevideo, 24 de julio de 1971) es un cantante, compositor y murguista uruguayo; "
                + "conocido por su participación en la murga Agarrate Catalina, conjunto que fundó junto a su hermano Yamandú y Carlos "
                + "Tanco en el año 2001.", "https://www.facebook.com/Tabar%C3%A9-Cardozo-55179094281/?ref=br_rs","");

        ICU.AgregarUsuarioProponente("cachilas", "Waldemar “Cachila”", "Silva", "Cachila.sil@c1080.org.uy", new GregorianCalendar(1947, 00, 01), ruta + "cachilas.jpg", "Br. Artigas 4567", "Nace en el año 1947 en el conventillo \\\"Medio Mundo\\\" ubicado en pleno Barrio Sur. Es heredero parcialmente-junto"
                + " al resto de sus hermanos- de la Comparsa \\\"Morenada\\\" (inactiva desde el fallecimiento de Juan Ángel Silva), en 1999 forma su "
                + "propia Comparsa de negros y lubolos \\\"Cuareim 1080\\\". Director responsable, compositor y cantante de la misma. ",
                "https://www.facebook.com/C1080?ref=br_rs","");

        ICU.AgregarUsuarioProponente("juliob", "Julio", "Bocca", "juliobocca@sodre.com.uy", new GregorianCalendar(1967, 02, 16), "",
                "Benito Blanco 4321", "", "","");

        ICU.AgregarUsuarioProponente("diegop", "Diego", "Parodi", "diego@efectocine.com", new GregorianCalendar(1975, 00, 01), "", "Emilio Frugoni 1138 Ap. 02", "", "http://www.efectocine.com","");

        ICU.AgregarUsuarioProponente("kairoh", "Kairo", "Herrera", "kairoher@pilsenrock.com.uy", new GregorianCalendar(1840, 03, 25),
                ruta + "kairoh.jpg", "Paraguay 1423", "", "","");

        ICU.AgregarUsuarioProponente("durazno", "Itendencia", "Durazno", "comunicacion@durazno.gub.uy", new GregorianCalendar(1980, 9, 31),
                ruta + "durazno.png", "8 de Octubre 1429", "Nuestros festivales que son orgullo del Uruguay, como cada enero con lo mejor del tango o el festival de la música "
                + "tropical. La fiesta de los jóvenes, donde miles de ellos disfrutan de variados espectáculos al aire libre y se confunden para vivir "
                + "tres días inolvidables, convocados por diversos géneros, como rock, tropical, murga y tango.", "http://durazno.gub.uy/portal/index.php","");

////-----------------------------------------------------Colaboradores------------------------------------//
        ICU.AgregarUsuarioColaborador("robinh", "Robin", "Henderson", "Robin.h@tinglesa.com.uy", new GregorianCalendar(1940, 07, 03), "","");

        ICU.AgregarUsuarioColaborador("marcelot", "Marcelo", "Tinelli", "marcelot@ideasdelsur.com.ar", new GregorianCalendar(1960, 03, 01), ruta + "marcelot.jpg","");

        ICU.AgregarUsuarioColaborador("novick", "Edgardo", "Novick", "edgardo@novick.com.uy", new GregorianCalendar(1960, 03, 01), ruta + "novick.jpg","");

        ICU.AgregarUsuarioColaborador("sergiop", "Sergio", "Puglia", "puglia@alpanpan.com.uy", new GregorianCalendar(1950, 00, 28), ruta + "sergiop.jpg","");

        ICU.AgregarUsuarioColaborador("chino", "Alvaro", "Recoba", "chino@trico.org.uy", new GregorianCalendar(1976, 02, 17), "","");

        ICU.AgregarUsuarioColaborador("tonyp", "Antonio", "Pacheco", "eltony@manya.org.uy", new GregorianCalendar(1995, 01, 14), "","");

        ICU.AgregarUsuarioColaborador("nicoJ", "Nicolás", "Jodal", "jodal@artech.com.uy", new GregorianCalendar(1960, 07, 9), ruta + "nicoJ.jpg","");

        ICU.AgregarUsuarioColaborador("juanP", "Juan", "Perez", "juanp@elpueblo.com", new GregorianCalendar(1970, 00, 01), "","");

        ICU.AgregarUsuarioColaborador("Mengano", "Mengano", "Gómez", "menganog@elpueblo.com", new GregorianCalendar(1982, 01, 02), "","");

        ICU.AgregarUsuarioColaborador("Perengano", "Perengano", "López", "pere@elpueblo.com", new GregorianCalendar(1985, 02, 03), "","");

        ICU.AgregarUsuarioColaborador("Tiajaci", "Tía", "Jacinta", "jacinta@elpueblo.com", new GregorianCalendar(1990, 03, 04), "","");

//-----------------------------------------------------Categorias------------------------------------//
        IPC.crearCategoriaDatosdePrueba("Categoria", null);
        IPC.crearCategoriaDatosdePrueba("Carnaval", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Cine", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Danza", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Literatura", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Musica", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Teatro", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Humoristas", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Lubolos", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Murga", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Parodistas", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Revista", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Cine a Pedal", "Cine");
        IPC.crearCategoriaDatosdePrueba("Cine al Aire Libre", "Cine");
        IPC.crearCategoriaDatosdePrueba("Ballet", "Danza");
        IPC.crearCategoriaDatosdePrueba("Flamenco", "Danza");
        IPC.crearCategoriaDatosdePrueba("Concierto", "Musica");
        IPC.crearCategoriaDatosdePrueba("Festival", "Musica");
        IPC.crearCategoriaDatosdePrueba("Comedia", "Teatro");
        IPC.crearCategoriaDatosdePrueba("Teatro Dramático", "Teatro");
        IPC.crearCategoriaDatosdePrueba("Teatro Musical", "Teatro");
        IPC.crearCategoriaDatosdePrueba("Stand-up", "Comedia");

//-----------------------------------------------------Propuestas------------------------------------//
        IPC.crearPropuestaDatosdePrueba("Cine en el Botánico", "El 16 de Diciembre a la hora 20 se proyectará la película \"Clever\", en el Jardín Botánico (Av. 19 de Abril 1181)\n"
                + "en el marco de las actividades realizadas por el ciclo Cultura al Aire Libre. El largometraje uruguayo de ficción\n"
                + "Clever es dirigido por Federico Borgia y Guillermo Madeiro. Es apto para mayores de 15 años.", IPC.ObtenerCategoria("Cine al Aire Libre"), new GregorianCalendar(2018, 8, 16),
                "Jardín Botánico ", 200, 150000, TipoRetorno.porGanancias, ICU.ObtenerProponente("diegop"), "Culturarte.png");

        IPC.crearPropuestaDatosdePrueba("Durazno Rock", "Una vez mas EspectaculosBA dice presente en DURAZNO ROCK! Mas de 400 personas nos acompañaron el\\r\\naño pasado y "
                + "este año se repite!! Este año con la presencia destacada de La Beriso!!", IPC.ObtenerCategoria("Festival"), new GregorianCalendar(2018, 11, 10),
                "Durazno", 0, 100000, TipoRetorno.porGanancias, ICU.ObtenerProponente("durazno"), "Culturarte.png");

        IPC.crearPropuestaDatosdePrueba("El Lazarillo de Tormes", "Vuelve unas de las producciones de El Galpón más aclamadas de los últimos tiempos. Esta obra se ha\\r\\npresentado "
                + "en Miami, Nueva York, Washington, México, Guadalajara, Río de Janeiro y La Habana. En nuestro\\r\\npaís, El Lazarillo de Tormes fue nominado en los rubros mejor espectáculo "
                + "y mejor dirección a los Premios\\r\\nFlorencio 1995, obteniendo su protagonista Héctor Guido el Florencio a Mejor actor de ese año.",
                IPC.ObtenerCategoria("Teatro Dramático"), new GregorianCalendar(2018, 11, 03), "Teatro el Galpón", 350, 1750000, TipoRetorno.Entradas, ICU.ObtenerProponente("hectorg"), "Culturarte.png");

        IPC.crearPropuestaDatosdePrueba("El Pimiento Indomable", "El Pimiento Indomable, formación compuesta por Kiko Veneno y el uruguayo Martín Buscaglia, presentará\\r\\neste 19 de Octubre, "
                + "su primer trabajo. Bajo un título homónimo al del grupo, es un disco que según los\\r\\npropios protagonistas “no se parece al de ninguno de los dos por separado. Entre los "
                + "títulos que se podrán\\r\\nescuchar se encuentran “Nadador salvador”, “América es más grande”, “Pescaito Enroscado” o “La reina del\\r\\nplacer", IPC.ObtenerCategoria("Concierto"), new GregorianCalendar(2018, 9, 19),
                "Teatro Solís", 400, 400000, TipoRetorno.porGanancias, ICU.ObtenerProponente("mbusca"), "El Pimiento Indomable.jpg");

        IPC.crearPropuestaDatosdePrueba("Pilsen Rock", "La edición 2018 del Pilsen Rock se celebrará el 21 de Octubre en la Rural del Prado y contará con la\\r\\nparticipación de "
                + "más de 15 bandas nacionales. Quienes no puedan trasladarse al lugar, "
                + "tendrán la posibilidad\\r\\nde disfrutar los shows a través de Internet, así como entrevistas en vivo a los músicos una vez finalizados los\\r\\nconciertos.",
                IPC.ObtenerCategoria("Festival"), new GregorianCalendar(2018, 9, 21), "Rural de Prado", 1000, 900000, TipoRetorno.EntGan, ICU.ObtenerProponente("kairoh"), "Pilsen Rock.jpg");

        IPC.crearPropuestaDatosdePrueba("Religiosamente", "MOMOSAPIENS presenta Religiosamente. Mediante dos parodias y un hilo conductor que aborda la temática\\r\\nde la religión "
                + "Momosapiens, mediante el humor y la reflexión, hilvana una historia que muestra al hombre\\r\\ninmerso en el tema religioso. El libreto está escrito utilizando diferentes"
                + " lenguajes de humor, dando una\\r\\nvisión satírica y reflexiva desde distintos puntos de vista, logrando mediante situaciones paródicas armar una\\r\\npropuesta plena de"
                + "arte carnavalero. ", IPC.ObtenerCategoria("Parodistas"), new GregorianCalendar(2018, 9, 07), "Teatro de Verano", 300, 300000, TipoRetorno.EntGan, ICU.ObtenerProponente("hrubino"), "Religiosamente.jpg");

        IPC.crearPropuestaDatosdePrueba("Romeo y Julieta", "Romeo y Julieta de Kenneth MacMillan, uno de los ballets favoritos del director artístico Julio Bocca, se\\r\\npresentará nuevamente "
                + "el 5 de Noviembre en el Auditorio Nacional del Sodre. Basada en la obra homónima\\r\\nde William Shakespeare, Romeo y Julieta es considerada la coreografía maestra del MacMillan. "
                + "La producción\\r\\nde vestuario y escenografía se realizó en los Talleres del Auditorio Adela Reta, sobre los diseños originales.", IPC.ObtenerCategoria("Ballet"), new GregorianCalendar(2018, 10, 05),
                "Auditorio Nacional del Sodre", 800, 750000, TipoRetorno.porGanancias, ICU.ObtenerProponente("juliob"),"Romeo y Julieta.jpg");

        IPC.crearPropuestaDatosdePrueba("Un día de Julio", "La Catalina presenta el espectáculo Un Día de Julio en Landia. Un hombre misterioso y solitario vive\\r\\nencerrado entre las cuatro paredes "
                + "de su casa. Intenta, con sus teorías extravagantes, cambiar el mundo\\r\\nexterior que le resulta inhabitable. Un día de Julio sucederá algo que cambiará su vida y la de su entorno "
                + "para\\r\\nsiempre.", IPC.ObtenerCategoria("Murga"), new GregorianCalendar(2018, 10, 16), "Landia", 650, 300000, TipoRetorno.EntGan, ICU.ObtenerProponente("tabarec"), "Un día de Julio.jpg");

        //------------------------------------------Seguidores--------------------------------------------------//
        try {
            ICU.seguirUsuario("chino", "tonyp");
            ICU.seguirUsuario("diegop", "durazno");
            ICU.seguirUsuario("diegop", "hrubino");
            ICU.seguirUsuario("durazno", "hrubino");
            ICU.seguirUsuario("durazno", "nicoJ");
            ICU.seguirUsuario("hectorg", "juliob");
            ICU.seguirUsuario("hectorg", "mbusca");
            ICU.seguirUsuario("hrubino", "diegop");
            ICU.seguirUsuario("hrubino", "durazno");
            ICU.seguirUsuario("hrubino", "hectorg");
            ICU.seguirUsuario("juliob", "diegop");
            ICU.seguirUsuario("juliob", "mbusca");
            ICU.seguirUsuario("kairoh", "sergiop");
            ICU.seguirUsuario("marcelot", "cachilas");
            ICU.seguirUsuario("marcelot", "juliob");
            ICU.seguirUsuario("marcelot", "kairoh");
            ICU.seguirUsuario("mbusca", "cachilas");
            ICU.seguirUsuario("mbusca", "kairoh");
            ICU.seguirUsuario("mbusca", "tabarec");
            ICU.seguirUsuario("Mengano", "hectorg");
            ICU.seguirUsuario("Mengano", "juliob");
            ICU.seguirUsuario("Mengano", "sergiop");
            ICU.seguirUsuario("nicoJ", "cachilas");
            ICU.seguirUsuario("nicoJ", "kairoh");
            ICU.seguirUsuario("nicoJ", "tabarec");
            ICU.seguirUsuario("novick", "cachilas");
            ICU.seguirUsuario("novick", "hrubino");
            ICU.seguirUsuario("novick", "tabarec");
            ICU.seguirUsuario("Perengano", "diegop");
            ICU.seguirUsuario("Perengano", "tonyp");
            ICU.seguirUsuario("robinh", "diegop");
            ICU.seguirUsuario("robinh", "hectorg");
            ICU.seguirUsuario("robinh", "juliob");
            ICU.seguirUsuario("sergiop", "diegop");
            ICU.seguirUsuario("sergiop", "juliob");
            ICU.seguirUsuario("sergiop", "mbusca");
            ICU.seguirUsuario("tabarec", "cachilas");
            ICU.seguirUsuario("tabarec", "hrubino");
            ICU.seguirUsuario("Tiajaci", "juliob");
            ICU.seguirUsuario("Tiajaci", "kairoh");
            ICU.seguirUsuario("Tiajaci", "sergiop");
            ICU.seguirUsuario("cachilas", "hrubino");
        } catch (Exception ex) {
            Logger.getLogger(Fabrica.class.getName()).log(Level.SEVERE, null, ex);
        }

//------------------------------------------Colaboraciones--------------------------------------------------//
        IPC.agregarColaboracionDatosdePrueba("Cine en el Botánico", "novick", 50000, new GregorianCalendar(2018, 04, 20, 14, 30, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Cine en el Botánico", "robinh", 50000, new GregorianCalendar(2018, 04, 24, 17, 25, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Cine en el Botánico", "nicoJ", 50000, new GregorianCalendar(2018, 04, 30, 18, 30, 0), false);

        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "marcelot", 200000, new GregorianCalendar(2018, 05, 30, 14, 25, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "Tiajaci", 500, new GregorianCalendar(2018, 06, 01, 18, 05, 0), true);
        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "Mengano", 600, new GregorianCalendar(2018, 06, 07, 17, 45, 0), true);
        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "novick", 50000, new GregorianCalendar(2018, 06, 10, 14, 35, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "sergiop", 50000, new GregorianCalendar(2018, 06, 15, 9, 45, 0), false);

        IPC.agregarColaboracionDatosdePrueba("El Pimiento Indomable", "marcelot", 200000, new GregorianCalendar(2018, 07, 01, 07, 40, 0), false);
        IPC.agregarColaboracionDatosdePrueba("El Pimiento Indomable", "sergiop", 80000, new GregorianCalendar(2018, 07, 03, 9, 25, 0), false);

        IPC.agregarColaboracionDatosdePrueba("Pilsen Rock", "chino", 50000, new GregorianCalendar(2018, 07, 05, 16, 50, 0), true);
        IPC.agregarColaboracionDatosdePrueba("Pilsen Rock", "novick", 120000, new GregorianCalendar(2018, 07, 10, 15, 50, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Pilsen Rock", "tonyp", 120000, new GregorianCalendar(2018, 00, 15, 19, 30, 0), true);

        IPC.agregarColaboracionDatosdePrueba("Romeo y Julieta", "sergiop", 100000, new GregorianCalendar(2018, 07, 13, 04, 58, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Romeo y Julieta", "marcelot", 200000, new GregorianCalendar(2018, 07, 14, 11, 25, 0), false);

        IPC.agregarColaboracionDatosdePrueba("Un día de Julio", "tonyp", 30000, new GregorianCalendar(2018, 07, 15, 04, 48, 0), true);
        IPC.agregarColaboracionDatosdePrueba("Un día de Julio", "marcelot", 150000, new GregorianCalendar(2018, 07, 17, 15, 30, 0), false);

//------------------------------------------Estado Propuesta--------------------------------------------------//
        IPC.nuevoEstadoPropuestaDatosdePrueba("Cine en el Botánico", TipoE.Ingresada, new GregorianCalendar(2018, 04, 15, 15, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Cine en el Botánico", TipoE.Publicada, new GregorianCalendar(2018, 04, 17, 8, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Cine en el Botánico", TipoE.enFinanciacion, new GregorianCalendar(2018, 04, 20, 14, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Cine en el Botánico", TipoE.Financiada, new GregorianCalendar(2018, 04, 30, 18, 30, 0));

        IPC.setEstadoActualDatosDePrueba("Cine en el Botánico", new EstadoPropuesta(TipoE.Cancelada, new GregorianCalendar(2018, 05, 15, 14, 50, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("Religiosamente", TipoE.Ingresada, new GregorianCalendar(2018, 05, 18, 4, 28, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Religiosamente", TipoE.Publicada, new GregorianCalendar(2018, 05, 20, 4, 56, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Religiosamente", TipoE.enFinanciacion, new GregorianCalendar(2018, 05, 30, 14, 25, 0));

        IPC.setEstadoActualDatosDePrueba("Religiosamente", new EstadoPropuesta(TipoE.Financiada, new GregorianCalendar(2018, 06, 15, 9, 45, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("El Pimiento Indomable", TipoE.Ingresada, new GregorianCalendar(2018, 06, 26, 15, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("El Pimiento Indomable", TipoE.Publicada, new GregorianCalendar(2018, 06, 31, 8, 30, 0));

        IPC.setEstadoActualDatosDePrueba("El Pimiento Indomable", new EstadoPropuesta(TipoE.enFinanciacion, new GregorianCalendar(2018, 07, 01, 7, 40, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("Pilsen Rock", TipoE.Ingresada, new GregorianCalendar(2018, 06, 30, 15, 40, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Pilsen Rock", TipoE.Publicada, new GregorianCalendar(2018, 07, 01, 14, 30, 0));

        IPC.setEstadoActualDatosDePrueba("Pilsen Rock", new EstadoPropuesta(TipoE.enFinanciacion, new GregorianCalendar(2018, 07, 05, 16, 50, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("Romeo y Julieta", TipoE.Ingresada, new GregorianCalendar(2018, 07, 04, 12, 20, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Romeo y Julieta", TipoE.Publicada, new GregorianCalendar(2018, 07, 10, 10, 25, 0));

        IPC.setEstadoActualDatosDePrueba("Romeo y Julieta", new EstadoPropuesta(TipoE.enFinanciacion, new GregorianCalendar(2018, 07, 13, 04, 58, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("Un día de Julio", TipoE.Ingresada, new GregorianCalendar(2018, 07, 06, 02, 00, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Un día de Julio", TipoE.Publicada, new GregorianCalendar(2018, 07, 12, 04, 50, 0));

        IPC.setEstadoActualDatosDePrueba("Un día de Julio", new EstadoPropuesta(TipoE.enFinanciacion, new GregorianCalendar(2018, 07, 15, 04, 48, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("El Lazarillo de Tormes", TipoE.Ingresada, new GregorianCalendar(2018, 07, 18, 02, 40, 0));
        IPC.setEstadoActualDatosDePrueba("El Lazarillo de Tormes", new EstadoPropuesta(TipoE.Publicada, new GregorianCalendar(2018, 07, 20, 02, 40, 0), true));

        IPC.setEstadoActualDatosDePrueba("Durazno Rock", new EstadoPropuesta(TipoE.Ingresada, new GregorianCalendar(2018, 07, 23, 2, 12, 0), true));

//    
        this.datosCargados = true;

    }

    public void LimpiarLogica() {

        IControladorUsuario ICU = this.getIControladorUsuario();
        IPropCat IPC = this.getControladorPropCat();

        Map<String, Usuario> usuarios = ICU.getUsuarios();
        Set set = usuarios.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Proponente) {
                Proponente p = (Proponente) mentry.getValue();
                ICU.borrarProponente(p.getNickname());
                iterator.remove();//aca se van borrando los usuarios proponentes
            }

            if (mentry.getValue() instanceof Colaborador) {
                Colaborador c = (Colaborador) mentry.getValue();
                ICU.borrarColaborador(c.getNickname());
                iterator.remove();//aca se van borrando los usuarios colaboradores
            }

        }
        ICU.getUsuarios().clear();
        IPC.getPropuestas().clear();//para limpiar el map dsp que borre todo
        ICU.eliminarCategorias();

        File directorioPerfiles = new File(System.getProperty("user.dir") + "\\fPerfiles");
        File[] ficherosPerfiles = directorioPerfiles.listFiles();
        for (int x = 0; x < ficherosPerfiles.length; x++) {
            if (ficherosPerfiles[x].getName().compareTo("nadie.png") != 0) {
                boolean estatus = ficherosPerfiles[x].delete();
            }
        }//Esto para limpiar las imagenes de la carpeta fPerfiles

        File directorioPropuestas = new File(System.getProperty("user.dir") + "\\fPropuestas\\");
        File[] ficherosPropuestas = directorioPropuestas.listFiles();
        for (int x = 0; x < ficherosPropuestas.length; x++) {
            if (ficherosPropuestas[x].getName().compareTo("Culturarte.png") != 0) {
                boolean estatus = ficherosPropuestas[x].delete();
            }
        }//Borra las imagenes de propuestas       
    }
}
