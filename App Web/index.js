var request = require("request");
var cheerio = require("cheerio");
var http = require("http");
var url = require('url');
var corpo;
const PORT = process.env.PORT || 5000

http.createServer(function (req, res) {

	intestazione='<head><meta charset="iso-8859-1" ><title>Ingnegneria Uniparthenope</title> <style type="text/css">a{color: #191970}.datatable {border: 2px solid #D6DDE6;	border-collapse: collapse;}.datatable td {border:2px solid #D6DDE6;padding: 0.3em;}.datatable th {border:2px solid #D6DDE6;background-color: #666666;color: #FFFFFF;font: bold 1.0em Georgia, serif;;padding-left: 0.3em;}.datatable tr.altrow {font: 0.8em Georgia, serif;;background-color: #cccccc;  color: #191970;	}.datatable tr.altrow2 { font: 0.8em Georgia, serif;;background-color: #ffffff;color: #191970;	}a {color: #000000;text-decoration: underline;}a:hover {text-decoration: none;}body {font-size: 10pt;font-family: Verdana;line-height: 14pt;background: #0080FF;color: #382b31;text-align:justify;}br.clear {clear: both;}h2,h3,h4 {margin-bottom: 1em;font-family: Verdana, serif;color: #000000;}strong {color: #000000;}img.left {float: left;margin: 8px 20px 20px 0;}img.top {margin: 8px 0 20px 0;}p {margin-bottom: 1.5em;}ul {margin-bottom: 1.5em;}.imageList {	list-style: none;}.imageList li {clear: both;}#bg {padding: 0 0 64px 0;}#content {width: 740px;margin: 0 0 0 192px;padding: 0;}#header {position: relative;padding: 24px;height: 150px;width: 940px;background: url("http://www.ingegneria.uniparthenope.it/images/pic1.jpg") repeat-x top right; color: #fff;}#main {position: relative;padding: 24px;width: 940px;color: #5e5e5e;}#main a {color: #1c1c1c;}#main h2, #main h3, #main h4 {color: #212121;}#main ul {list-style: none;}#main ul li.first {padding-top: 0;border-top: 0;}#main ul.imageList li {padding: 16px 0 16px 0;}#nav {position: absolute;bottom: 0;left:0;height: 50px;line-height: 45px;padding: 0 24px 0 24px;width: 940px;	margin: 0 0 0 0;background: #0062E1;}#nav a {text-decoration: none;text-transform: lowercase;color: #fff;}#nav li {margin: 0 1em 0 0;padding: 0 1em 0 1em;}#nav li.active {background: #fff;}#nav li.active a {color: #4e7068;}#nav ul {	list-style: none;}#nav ul li {	float: left;}#outer {position: relative;top:10px;width: 988px;	margin: 0 auto 0 auto;	background: #fff;}#sidebar {width: 172px;float: left;padding: 0;}div.news{margin-top:0.4em}div.news h4{padding-left: 19px;background: url("http://www.ingegneria.uniparthenope.it/images/bandi.png") no-repeat center left}div.news h4 span.data{color: #656969;font-weight: lighter;font-size: 80%}div.bandi{margin-top:0.4em}div.bandi h4{padding-left: 19px;background: url("images/bandi.png") no-repeat center left}div.bandi h4 span.data{color: #656969;font-weight: lighter;font-size: 80%}</style><meta http-equiv="Content-Type" content="Type=text/html; charset=utf-8"></head>';
	res.write(intestazione);
	res.write('<body>');
	var q = url.parse(req.url).pathname;
	if (q == '/') {
		res.write('<div id="bg" ><div id="outer"><div id="header"><div id="nav" ><ul  ><h3><li class="first active"><a href="./">Home</a></li><li class=""><a href="./orari">Orario Lezioni</a></li><li><a href="./avvisi">News</a></li><li><a href="./professori">Docenti</a></li><li><a href="./personale">Personale</a></li><li><a href="./dovesiamo">Dove siamo</a></li><li><a href="./link_utili">link utili</a></li></h3></ul><br class="clear __web-inspector-hide-shortcut__"></div></div>');		
		res.write('<div id="main">Cos&#8217&#232; questo sito?</h1><p>Questo sito &egrave; stato creato dagli studenti del corso magistrale di &#8220Ingegneria della Sicurezza dei Dati e delle Comunicazioni&#8221 presso l&#8217universit&agrave; Parthenope di Napoli.</p><p>La nostra idea era creare un sito che replicasse il funzionamento dell&#8217applicazione Android sviluppata come progetto per il corso di &#8220Applicazioni web e per sistemi mobili&#8221.<div align="center"><img src="https://www.anciu.it/wp-content/uploads/2017/10/459_parthenope.png"></div></div>');
		res.write('</div>');
		res.write('</div>');
		res.end();
	}else if (q=='/avvisi'){		
			res.write('<div id="bg"><div id="outer"><div id="header"><div id="nav" ><ul class=""><h3><li><a href="./">Home</a></li><li class=""><a href="./orari">Orario Lezioni</a></li><li class="first active"><a href="./avvisi">News</a></li><li><a href="./professori">Docenti</a></li><li><a href="./personale">Personale</a></li><li><a href="./dovesiamo">Dove siamo</a></li><li><a href="./link_utili">link utili</a></li></h3></ul><br class="clear __web-inspector-hide-shortcut__"></div></div>');		
			res.write('<div id="main">');
			res.write('<h1>AVVISI</h1>');
			request({   uri: "http://www.ingegneria.uniparthenope.it/index.php?page=news",
			//uri: "http://insegnamentoadistanza.altervista.org/ingegneria/Dipartimento_di_IngegneriaAVVISI.html",
			}, function(error, response, body) {
					if (!error && response.statusCode == 200){
						var $ = cheerio.load(body);
						$('a').each(function(i,element){
							var b = $(this).attr('href').substring(0,1);
							var c = $(this).attr('href').substring(1);
							if (b=='.'){
								$(this).attr('href','http://www.ingegneria.uniparthenope.it'+c);
							}
						});
						$('div.news').each(function(i,element){
							var a=$(this);
							res.write(a.html());
						});
		
					};
					res.end('</div>');
			}); 
	}
	
	if (q=='/orari'){
	
		res.write('<div id="bg"><div id="outer"><div id="header"><div id="nav" ><ul class=""><h3><li><a href="./">Home</a></li><li class="first active"><a href="./orari">Orario Lezioni</a></li><li><a href="./avvisi">News</a></li><li><a href="./professori">Docenti</a></li><li><a href="./personale">Personale</a></li><li><a href="./dovesiamo">Dove siamo</a></li><li><a href="./link_utili">link utili</a></li></h3></ul><br class="clear __web-inspector-hide-shortcut__"></div></div>');		
		res.write('<div id="main">');
		res.write('<p></p><p></p>');
		res.write('<p><h1>Orario lezioni</h1></p>');
		res.write('<p><h3><a href="http://www.ingegneria.uniparthenope.it/orario/civile.pdf">Ingegneria Civile e Ambientale</a></h3></p>');
		res.write('<h3><a href="http://www.ingegneria.uniparthenope.it/orario/civile_LM.pdf">Ingegneria Civile Magistale</a></h3></p>');
		res.write('<p><h3><a href="http://www.ingegneria.uniparthenope.it/orario/gestionale.pdf">Ingegneria Gestionale</a></h3></p>');
		res.write('<p><h3><a href="http://www.ingegneria.uniparthenope.it/orario/gestionale_LM.pdf">Ingegneria Gestionale Magistale</a></h3></p>');
		res.write('<p><h3><a href="http://www.ingegneria.uniparthenope.it/orario/inf_bio_tlc.pdf">Ingegneria Informatica, Biomedica e delle Telecomunicazioni</a></h3></p>');
		res.write('<p><h3><a href="http://www.ingegneria.uniparthenope.it/orario/ISDC.pdf">Ingegneria della Sicurezza dei Dati e delle Comunicazioni</a></h3></p>');
	    res.end('</div>'); 
	}
	 
	if (q=='/professori')  {
		res.write('<div id="bg"><div id="outer"><div id="header"><div id="nav" ><ul class=""><h3><li><a href="./">Home</a></li><li class=""><a href="./orari">Orario Lezioni</a></li><li><a href="./avvisi">News</a></li><li class="first active"><a href="./professori">Docenti</a></li><li><a href="./personale">Personale</a></li><li><a href="./dovesiamo">Dove siamo</a></li><li><a href="./link_utili">link utili</a></li><h3></ul><br class="clear __web-inspector-hide-shortcut__"></div></div>');		
		res.write('<div id="main">');
		request({	uri: "http://www.ingegneria.uniparthenope.it/index.php?page=personale",
		//uri: "http://insegnamentoadistanza.altervista.org/ingegneria/prof_Dipartimento_di_Ingegneria.html"
		}, function(error, response, body) {
				if (!error && response.statusCode == 200){
					var $ = cheerio.load(body);
					res.write('<h1 align="center">Professori</h1>');
					res.write('<table class="datatable" align="center">');
					res.write($('table.datatable').eq(0).html());
					res.end('</table>');
				};
				res.end('</div>');
		}); 
	};
	
	if (q=='/personale') 	{
		res.write('<div id="bg"><div id="outer"><div id="header"><div id="nav"><ul class=""><h3><li><a href="./">Home</a></li><li class=""><a href="./orari">Orario Lezioni</a></li><li><a href="./avvisi">News</a></li><li><a href="./professori">Docenti</a></li><li class="first active"><a href="./personale">Personale</a></li><li><a href="./dovesiamo">Dove siamo</a></li><li><a href="./link_utili">link utili</a></li></h3></ul><br class="clear __web-inspector-hide-shortcut__"></div></div>');		
		res.write('<div id="main">');
		request({	uri: "http://www.ingegneria.uniparthenope.it/index.php?page=personale",
		//uri: "http://insegnamentoadistanza.altervista.org/ingegneria/prof_Dipartimento_di_Ingegneria.html"
		}, function(error, response, body) {
				if (!error && response.statusCode == 200){
					var $ = cheerio.load(body);
					res.write('<h1 align="center" >Personale tecnico-amministrativo</h1>');
					res.write('<table class="datatable" align="center">');
					res.write($('table.datatable').eq(1).html());
					res.end('</table>');
				};
		});
	};

	if (q=='/dovesiamo') 	{
		res.write('<div id="bg"><div id="outer"><div id="header"><div id="nav" ><ul class=""><h3><li><a href="./">Home</a></li><li class=""><a href="./orari">Orario Lezioni</a></li><li><a href="./avvisi">News</a></li><li><a href="./professori">Docenti</a></li><li><a href="./personale">Personale</a></li><li class="first active"><a href="./dovesiamo">Dove siamo</a></li><li><a href="./link_utili">link utili</a></li></h3></ul><br class="clear __web-inspector-hide-shortcut__"></div></div>');		
		res.write('<div id="main">');
		res.write('<div ><div class="testo"><ol type="disc"><table align="center" border="0"><tbody><tr><td valign="top"><h3>Dipartimento di Ingegneria - Universita degli Studi di Napoli Parthenope</h3></td></tr><tr><td align="center" valign="top"><a href="https://www.google.it/maps/place/Universit%C3%A0+Degli+Studi+Parthenope+-+Dipartimento+di+Scienze+e+Tecnologie/@40.856969,14.2819253,17z/data=!4m8!1m2!3m1!2sUniversit%C3%A0+Degli+Studi+Di+Napoli+%22Parthenope%22!3m4!1s0x133b081d0d41dcf7:0x66fca2754668f01b!8m2!3d40.8563281!4d14.2846084"><h3>Centro Direzionale Isola C4 - Napoli CAP 80143</h3></a></td></tr></tbody></table><p></p><li><strong>Segreteria Didattica</strong> : Sesto Piano, Lato Sud stanza 610</li>Tel.: 0815476788/6737/6796 - Fax: 0815476780<li><strong>Direzione Dipartimento</strong> : Sesto Piano, Lato Sud stanza 610/B</li>Tel.: 0815476712 - Fax: 0815476777<li><strong>Segretario Amministrativo</strong> : Sesto Piano, Lato Sud stanza 607/W</li>Tel.: 0815476787 - Fax: 0815476777<li><strong>Segreteria Amministrativa</strong>: Sesto Piano, Lato Sud stanza 607/E</li>Tel.: 0815476616/6742 - Fax: 0815476777<li><strong>Indirizzo di posta elettronica certificata</strong>: <a href="mailto:dipartimento.ingegneria@pec.uniparthenope.it">dipartimento.ingegneria@pec.uniparthenope.it</a></li><p></p></ol></div></div>');
		res.end('</div>');
	};

	if (q=='/link_utili'){
	
		res.write('<div id="bg"><div id="outer"><div id="header"><div id="nav" ><ul class=""><h3><li><a href="./">Home</a></li><li ><a href="./orari">Orario Lezioni</a></li><li><a href="./avvisi">News</a></li><li><a href="./professori">Docenti</a></li><li><a href="./personale">Personale</a></li><li><a href="./dovesiamo">Dove siamo</a></li><li class="first active"><a href="./link_utili">link utili</a></li></h3></ul><br class="clear __web-inspector-hide-shortcut__"></div></div>');		
		res.write('<div id="main">');
		res.write('<p></p><p></p>');
		res.write('<p><h1>Link Utili</h1></p>');
		res.write('<p><h3><a href="https://it-it.facebook.com/ingegneria.uniparthenope/">Gruppo Facebook</a></h3></p>');
		res.write('<p><h3><a href="http://edi.uniparthenope.it/">EDI Parthenope</a></h3></p>');
		res.write('<p><h3><a href="https://uniparthenope.esse3.cineca.it/Home.do">ESSE3 Cineca</a></h3></p>');
		res.write('<p><h3><a href="https://www.uniparthenope.it/">Sito di Ateneo</a></h3></p>');
		res.write('<h3><a href="http://www.ingegneria.uniparthenope.it/studenti/index.php?page=mod_stud">Modulistica</a></h3></p>');
        res.write('<p><h3><a href="http://www.cartastudenti.org/?m=1">Carta Studenti</a></h3></p>');
		res.end('</div>'); }

}).listen(PORT);
	