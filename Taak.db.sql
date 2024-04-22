BEGIN TRANSACTION;
DROP TABLE IF EXISTS "Scheidsen";
CREATE TABLE IF NOT EXISTS "Scheidsen" (
	"SpelerID"	INTEGER NOT NULL,
	"Arbiter-Ranking"	INTEGER,
	PRIMARY KEY("SpelerID"),
	FOREIGN KEY("SpelerID") REFERENCES "Spelers"("SpelerID") ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "Wedstrijdleiders";
CREATE TABLE IF NOT EXISTS "Wedstrijdleiders" (
	"SpelerID"	INTEGER NOT NULL,
	PRIMARY KEY("SpelerID"),
	FOREIGN KEY("SpelerID") REFERENCES "Spelers"("SpelerID") ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "Adressen";
CREATE TABLE IF NOT EXISTS "Adressen" (
	"AdresID"	INTEGER NOT NULL,
	"Straatnaam"	TEXT NOT NULL,
	"Straatnummer"	INTEGER NOT NULL,
	"Postcode"	INTEGER NOT NULL CHECK(LENGTH("Postcode") = 4),
	PRIMARY KEY("AdresID")
);
DROP TABLE IF EXISTS "SpelersEmailadressen";
CREATE TABLE IF NOT EXISTS "SpelersEmailadressen" (
	"spelerID"	INTEGER NOT NULL COLLATE BINARY,
	"Email"	TEXT NOT NULL CHECK("EMAIL" LIKE ('_%@_%._%')) COLLATE NOCASE,
	PRIMARY KEY("spelerID","Email"),
	FOREIGN KEY("spelerID") REFERENCES "Spelers"("SpelerID") ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "Datums";
CREATE TABLE IF NOT EXISTS "Datums" (
	"DatumID"	INTEGER NOT NULL,
	"Dag"	INTEGER NOT NULL CHECK("DAG" <= 31),
	"Maand"	INTEGER NOT NULL CHECK("MAAND" <= 12),
	"Jaar"	INTEGER NOT NULL,
	"Uur"	INTEGER CHECK("Uur" <= 23),
	"Minuten"	INTEGER CHECK("Minuten" <= 59),
	PRIMARY KEY("DatumID" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "Velden";
CREATE TABLE IF NOT EXISTS "Velden" (
	"VeldID"	INTEGER NOT NULL,
	"VeldSoort"	INTEGER NOT NULL,
	"ClubID"	INTEGER NOT NULL,
	PRIMARY KEY("VeldID"),
	FOREIGN KEY("ClubID") REFERENCES "Tennisclubs"("ClubID") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("VeldSoort") REFERENCES "VeldSoort_ENUM"("id") ON UPDATE RESTRICT ON DELETE RESTRICT
);
DROP TABLE IF EXISTS "Finales";
CREATE TABLE IF NOT EXISTS "Finales" (
	"MatchID"	INTEGER NOT NULL,
	"Scheidsrechter"	INTEGER NOT NULL,
	PRIMARY KEY("MatchID"),
	FOREIGN KEY("MatchID") REFERENCES "Matchen"("MatchID") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("Scheidsrechter") REFERENCES "Scheidsen"("SpelerID") ON UPDATE CASCADE ON DELETE RESTRICT
);
DROP TABLE IF EXISTS "Supporters";
CREATE TABLE IF NOT EXISTS "Supporters" (
	"SpelerID"	INTEGER NOT NULL,
	"ClubID"	INTEGER NOT NULL,
	PRIMARY KEY("SpelerID"),
	FOREIGN KEY("SpelerID") REFERENCES "Spelers"("SpelerID") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("ClubID") REFERENCES "Tennisclubs"("ClubID") ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "Ballenrapers";
CREATE TABLE IF NOT EXISTS "Ballenrapers" (
	"SpelerID"	INTEGER NOT NULL,
	"PlaatsID"	INTEGER NOT NULL,
	PRIMARY KEY("SpelerID"),
	FOREIGN KEY("PlaatsID") REFERENCES "Plaatsen_ENUM"("PlaatsID") ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY("SpelerID") REFERENCES "Spelers"("SpelerID") ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "Supporterde";
CREATE TABLE IF NOT EXISTS "Supporterde" (
	"SupperterID"	INTEGER NOT NULL,
	"FinaleID"	INTEGER NOT NULL,
	PRIMARY KEY("SupperterID","FinaleID"),
	FOREIGN KEY("SupperterID") REFERENCES "Supporters"("SpelerID") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("FinaleID") REFERENCES "Finales"("MatchID") ON UPDATE CASCADE ON DELETE RESTRICT
);
DROP TABLE IF EXISTS "GeraapteBallen";
CREATE TABLE IF NOT EXISTS "GeraapteBallen" (
	"BallenraperID"	INTEGER NOT NULL,
	"FinaleID"	INTEGER NOT NULL,
	PRIMARY KEY("BallenraperID","FinaleID"),
	FOREIGN KEY("BallenraperID") REFERENCES "Ballenrapers"("SpelerID") ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY("FinaleID") REFERENCES "Finales"("MatchID") ON UPDATE CASCADE ON DELETE RESTRICT
);
DROP TABLE IF EXISTS "Matchen";
CREATE TABLE IF NOT EXISTS "Matchen" (
	"MatchID"	INTEGER NOT NULL,
	"Uitslag"	INTEGER NOT NULL,
	"ScoreUit"	INTEGER NOT NULL,
	"ScoreThuis"	INTEGER NOT NULL,
	"Datum"	INTEGER NOT NULL,
	"Wedstrijdleider"	INTEGER NOT NULL,
	"Veld"	INTEGER NOT NULL,
	"ToernooiID"	INTEGER NOT NULL,
	PRIMARY KEY("MatchID"),
	FOREIGN KEY("ToernooiID") REFERENCES "Toernooien"("ToornooiID") ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY("Datum") REFERENCES "Datums"("DatumID") ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY("Veld") REFERENCES "Velden"("VeldID") ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY("Wedstrijdleider") REFERENCES "Wedstrijdleiders"("SpelerID") ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY("Uitslag") REFERENCES "Uitslagen_ENUM"("UitslagID") ON UPDATE RESTRICT ON DELETE RESTRICT
);
DROP TABLE IF EXISTS "Reeksen";
CREATE TABLE IF NOT EXISTS "Reeksen" (
	"ReeksID"	INTEGER NOT NULL,
	PRIMARY KEY("ReeksID")
);
DROP TABLE IF EXISTS "ToernooienReeksen";
CREATE TABLE IF NOT EXISTS "ToernooienReeksen" (
	"ToernooiID"	INTEGER NOT NULL,
	"ReeksID"	INTEGER NOT NULL,
	PRIMARY KEY("ReeksID","ToernooiID"),
	FOREIGN KEY("ToernooiID") REFERENCES "Toernooien"("ToornooiID") ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY("ReeksID") REFERENCES "Reeksen"("ReeksID") ON UPDATE CASCADE ON DELETE RESTRICT
);
DROP TABLE IF EXISTS "Deelnamen";
CREATE TABLE IF NOT EXISTS "Deelnamen" (
	"DeelnameID"	INTEGER NOT NULL,
	"SpelerID"	INTEGER NOT NULL,
	"MatchID"	INTEGER NOT NULL,
	"Vraag"	TEXT,
	PRIMARY KEY("DeelnameID" AUTOINCREMENT),
	FOREIGN KEY("SpelerID") REFERENCES "Spelers"("SpelerID") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("MatchID") REFERENCES "Matchen"("MatchID") ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "NiveauReeks";
CREATE TABLE IF NOT EXISTS "NiveauReeks" (
	"ReeksID"	INTEGER NOT NULL,
	"Niveau"	TEXT NOT NULL,
	PRIMARY KEY("Niveau","ReeksID"),
	FOREIGN KEY("ReeksID") REFERENCES "Reeksen"("ReeksID") ON UPDATE CASCADE ON DELETE RESTRICT
);
DROP TABLE IF EXISTS "Spelers";
CREATE TABLE IF NOT EXISTS "Spelers" (
	"SpelerID"	INTEGER NOT NULL UNIQUE,
	"Naam"	TEXT NOT NULL,
	"Telefoonnummer"	TEXT NOT NULL,
	"Geboortedatum"	INTEGER NOT NULL,
	"Gewicht"	NUMERIC NOT NULL,
	"Lengte"	NUMERIC NOT NULL,
	"Ranking"	INTEGER,
	"Geslacht"	TEXT NOT NULL CHECK("Geslacht" IN ('M', 'V', 'X')),
	"Reeks"	INTEGER NOT NULL,
	"Club"	INTEGER NOT NULL,
	PRIMARY KEY("SpelerID"),
	FOREIGN KEY("Club") REFERENCES "Tennisclubs"("ClubID") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("Reeks") REFERENCES "Reeksen"("ReeksID") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("Geboortedatum") REFERENCES "Datums"("DatumID")
);
DROP TABLE IF EXISTS "Tennisclubs";
CREATE TABLE IF NOT EXISTS "Tennisclubs" (
	"ClubID"	INTEGER NOT NULL,
	"Naam"	TEXT NOT NULL,
	"Adres"	INTEGER NOT NULL,
	PRIMARY KEY("ClubID"),
	FOREIGN KEY("Adres") REFERENCES "Adressen" ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "Toernooien";
CREATE TABLE IF NOT EXISTS "Toernooien" (
	"ToornooiID"	INTEGER NOT NULL,
	"BeginDatumID"	INTEGER NOT NULL,
	"EindDatumID"	INTEGER NOT NULL,
	"ClubID"	INTEGER NOT NULL,
	PRIMARY KEY("ToornooiID"),
	FOREIGN KEY("EindDatumID") REFERENCES "Datums"("DatumID") ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY("BeginDatumID") REFERENCES "Datums"("DatumID") ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY("ClubID") REFERENCES "Tennisclubs"("ClubID") ON UPDATE CASCADE ON DELETE RESTRICT
);
DROP TABLE IF EXISTS "Uitslagen_ENUM";
CREATE TABLE IF NOT EXISTS "Uitslagen_ENUM" (
	"UitslagID"	INTEGER NOT NULL,
	"Soort"	TEXT NOT NULL,
	PRIMARY KEY("UitslagID" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "Plaatsen_ENUM";
CREATE TABLE IF NOT EXISTS "Plaatsen_ENUM" (
	"PlaatsID"	INTEGER NOT NULL,
	"Plaats"	TEXT NOT NULL,
	PRIMARY KEY("PlaatsID")
);
DROP TABLE IF EXISTS "VeldSoort_ENUM";
CREATE TABLE IF NOT EXISTS "VeldSoort_ENUM" (
	"id"	INTEGER NOT NULL,
	"SOORT"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "Scheidsen" ("SpelerID","Arbiter-Ranking") VALUES (2,NULL);
INSERT INTO "Wedstrijdleiders" ("SpelerID") VALUES (1);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (1,'Schoolstraat',253,4280);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (2,'Eikstraat',361,3460);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (3,'Westdorp',8,1495);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (4,'Ramselsesteenweg',477,1300);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (5,'Kasterleesteenweg',27,5060);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (6,'Cederstraat',52,8570);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (7,'Karolusguldenstraat',36,5230);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (8,'Rotscheweg',2,8700);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (9,'Aldegeasterdijk',85,4610);
INSERT INTO "Adressen" ("AdresID","Straatnaam","Straatnummer","Postcode") VALUES (10,'Mathenessestraat',108,2510);
INSERT INTO "SpelersEmailadressen" ("spelerID","Email") VALUES (1,'LazarPollemans@armyspy.com');
INSERT INTO "SpelersEmailadressen" ("spelerID","Email") VALUES (1,'EnaTijhof@armyspy.com');
INSERT INTO "SpelersEmailadressen" ("spelerID","Email") VALUES (2,'AbdulOevering@jourrapide.com');
INSERT INTO "SpelersEmailadressen" ("spelerID","Email") VALUES (3,'PippiKonst@dayrep.com');
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (1,1,1,2024,10,23);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (2,2,2,2024,1,1);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (3,3,3,2024,2,2);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (4,4,4,2024,3,3);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (5,5,5,2024,4,4);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (6,6,6,2024,5,5);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (7,7,7,2024,6,6);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (8,8,8,2024,7,7);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (9,9,9,2024,8,8);
INSERT INTO "Datums" ("DatumID","Dag","Maand","Jaar","Uur","Minuten") VALUES (10,10,10,2024,9,9);
INSERT INTO "Velden" ("VeldID","VeldSoort","ClubID") VALUES (1,1,1);
INSERT INTO "Velden" ("VeldID","VeldSoort","ClubID") VALUES (2,3,4);
INSERT INTO "Velden" ("VeldID","VeldSoort","ClubID") VALUES (3,2,1);
INSERT INTO "Velden" ("VeldID","VeldSoort","ClubID") VALUES (4,3,1);
INSERT INTO "Velden" ("VeldID","VeldSoort","ClubID") VALUES (5,2,2);
INSERT INTO "Velden" ("VeldID","VeldSoort","ClubID") VALUES (6,3,3);
INSERT INTO "Finales" ("MatchID","Scheidsrechter") VALUES (1,2);
INSERT INTO "Supporters" ("SpelerID","ClubID") VALUES (3,1);
INSERT INTO "Ballenrapers" ("SpelerID","PlaatsID") VALUES (2,0);
INSERT INTO "Supporterde" ("SupperterID","FinaleID") VALUES (3,1);
INSERT INTO "GeraapteBallen" ("BallenraperID","FinaleID") VALUES (2,1);
INSERT INTO "Matchen" ("MatchID","Uitslag","ScoreUit","ScoreThuis","Datum","Wedstrijdleider","Veld","ToernooiID") VALUES (1,0,0,2,5,1,3,1);
INSERT INTO "Matchen" ("MatchID","Uitslag","ScoreUit","ScoreThuis","Datum","Wedstrijdleider","Veld","ToernooiID") VALUES (2,1,0,2,3,1,1,2);
INSERT INTO "Matchen" ("MatchID","Uitslag","ScoreUit","ScoreThuis","Datum","Wedstrijdleider","Veld","ToernooiID") VALUES (3,2,3,3,9,1,3,2);
INSERT INTO "Reeksen" ("ReeksID") VALUES (1);
INSERT INTO "Reeksen" ("ReeksID") VALUES (2);
INSERT INTO "Reeksen" ("ReeksID") VALUES (3);
INSERT INTO "Reeksen" ("ReeksID") VALUES (4);
INSERT INTO "Reeksen" ("ReeksID") VALUES (5);
INSERT INTO "Reeksen" ("ReeksID") VALUES (6);
INSERT INTO "ToernooienReeksen" ("ToernooiID","ReeksID") VALUES (1,1);
INSERT INTO "ToernooienReeksen" ("ToernooiID","ReeksID") VALUES (1,2);
INSERT INTO "ToernooienReeksen" ("ToernooiID","ReeksID") VALUES (1,3);
INSERT INTO "ToernooienReeksen" ("ToernooiID","ReeksID") VALUES (1,4);
INSERT INTO "ToernooienReeksen" ("ToernooiID","ReeksID") VALUES (2,5);
INSERT INTO "ToernooienReeksen" ("ToernooiID","ReeksID") VALUES (2,6);
INSERT INTO "Deelnamen" ("DeelnameID","SpelerID","MatchID","Vraag") VALUES (1,1,1,NULL);
INSERT INTO "Deelnamen" ("DeelnameID","SpelerID","MatchID","Vraag") VALUES (2,2,1,NULL);
INSERT INTO "Deelnamen" ("DeelnameID","SpelerID","MatchID","Vraag") VALUES (3,3,2,'Help');
INSERT INTO "Deelnamen" ("DeelnameID","SpelerID","MatchID","Vraag") VALUES (4,1,2,'Geen');
INSERT INTO "Deelnamen" ("DeelnameID","SpelerID","MatchID","Vraag") VALUES (5,2,3,'Niks');
INSERT INTO "Deelnamen" ("DeelnameID","SpelerID","MatchID","Vraag") VALUES (6,3,3,NULL);
INSERT INTO "NiveauReeks" ("ReeksID","Niveau") VALUES (1,'Enkel Heren t.e.m. 5 punten');
INSERT INTO "NiveauReeks" ("ReeksID","Niveau") VALUES (2,'enkel Heren t.e.m. 10 punten');
INSERT INTO "NiveauReeks" ("ReeksID","Niveau") VALUES (3,'enkel Dames t.e.m. 5
punten,');
INSERT INTO "NiveauReeks" ("ReeksID","Niveau") VALUES (4,'enkel Dames t.e.m. 10 punten');
INSERT INTO "NiveauReeks" ("ReeksID","Niveau") VALUES (5,'Enkel Heren');
INSERT INTO "NiveauReeks" ("ReeksID","Niveau") VALUES (6,'Enkel Dames');
INSERT INTO "Spelers" ("SpelerID","Naam","Telefoonnummer","Geboortedatum","Gewicht","Lengte","Ranking","Geslacht","Reeks","Club") VALUES (1,'Sem Schiks','0481327071',9,60.35,181.25,NULL,'M',1,2);
INSERT INTO "Spelers" ("SpelerID","Naam","Telefoonnummer","Geboortedatum","Gewicht","Lengte","Ranking","Geslacht","Reeks","Club") VALUES (2,'Rosanne Struijs','0470930165',4,50.25,156.2,NULL,'V',2,2);
INSERT INTO "Spelers" ("SpelerID","Naam","Telefoonnummer","Geboortedatum","Gewicht","Lengte","Ranking","Geslacht","Reeks","Club") VALUES (3,'Yrsa van der Gugten','0498220260',3,78.12,198.72,NULL,'X',6,4);
INSERT INTO "Tennisclubs" ("ClubID","Naam","Adres") VALUES (1,'KLTV',2);
INSERT INTO "Tennisclubs" ("ClubID","Naam","Adres") VALUES (2,'LTC NOMI',3);
INSERT INTO "Tennisclubs" ("ClubID","Naam","Adres") VALUES (3,'BLTC',5);
INSERT INTO "Tennisclubs" ("ClubID","Naam","Adres") VALUES (4,'DSV Concordia',7);
INSERT INTO "Toernooien" ("ToornooiID","BeginDatumID","EindDatumID","ClubID") VALUES (0,10,8,4);
INSERT INTO "Toernooien" ("ToornooiID","BeginDatumID","EindDatumID","ClubID") VALUES (1,1,2,1);
INSERT INTO "Toernooien" ("ToornooiID","BeginDatumID","EindDatumID","ClubID") VALUES (2,3,5,4);
INSERT INTO "Uitslagen_ENUM" ("UitslagID","Soort") VALUES (0,'Gewonnen');
INSERT INTO "Uitslagen_ENUM" ("UitslagID","Soort") VALUES (1,'Verloren');
INSERT INTO "Uitslagen_ENUM" ("UitslagID","Soort") VALUES (2,'Gelijkspel');
INSERT INTO "Uitslagen_ENUM" ("UitslagID","Soort") VALUES (3,'Niet Gespeeld');
INSERT INTO "Plaatsen_ENUM" ("PlaatsID","Plaats") VALUES (0,'Net');
INSERT INTO "Plaatsen_ENUM" ("PlaatsID","Plaats") VALUES (1,'Zijkant');
INSERT INTO "Plaatsen_ENUM" ("PlaatsID","Plaats") VALUES (2,'Achterkant');
INSERT INTO "VeldSoort_ENUM" ("id","SOORT") VALUES (0,'Gravel');
INSERT INTO "VeldSoort_ENUM" ("id","SOORT") VALUES (1,'Dirt');
INSERT INTO "VeldSoort_ENUM" ("id","SOORT") VALUES (2,'Kunstgras');
INSERT INTO "VeldSoort_ENUM" ("id","SOORT") VALUES (3,'Gras');
COMMIT;
