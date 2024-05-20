
-- Adressen van Tennis clubs
INSERT INTO "adressen" (adresID, postcode, straatnaam, straatnummer) VALUES (1, 3530, 'maartenhoek', 30);
INSERT INTO "adressen" (adresID, postcode, straatnaam, straatnummer) VALUES (2, 1000, 'Nieuwstraat', 5);
INSERT INTO "adressen" (adresID, postcode, straatnaam, straatnummer) VALUES (3, 2000, 'Lange Lozanastraat', 123);

--Tennis clubs
INSERT INTO "tennisclubs" (clubID, naam, adresID) values (1, 'TestClub', 1);
INSERT INTO "tennisclubs" (clubID, naam, adresID) VALUES (2, 'TennisClub Brussels', 2);
INSERT INTO "tennisclubs" (clubID, naam, adresID) VALUES (3, 'Antwerp Tennis Club', 3);

-- Datums van spelers
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (1,27, 2004, 9, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (2,3, 2004, 2, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (3, 13, 2003, 6, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (4, 22, 2002, 12, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (5, 15, 2001, 3, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (6, 8, 2000, 7, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (7, 30, 2005, 5, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (8, 19, 2003, 8, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (9, 10, 2004, 11, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (10, 5, 2002, 1, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (11, 17, 2003, 4, null, null);

-- Spelers
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (12345678, 'M', 75, 195, 'Vince', 1, 0484360325, 1, 1);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (98765432, 'M', 82, 190, 'Daan', 1, 0484360325, 2, 2);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (97865342, 'M', 65, 180, 'Thibo', 1, 0484360325, 3, 3);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (23456789, 'V', 60, 170, 'Anna', 2, 0484360326, 4, 1);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (34567890, 'M', 78, 185, 'Tom', 3, 0484360327, 5, 1);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (45678901, 'V', 55, 165, 'Lies', 4, 0484360328, 6, 1);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (56789012, 'M', 80, 192, 'Jan', 5, 0484360329, 7, 1);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (67890123, 'V', 68, 175, 'Eva', 6, 0484360330, 8, 1);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (78901234, 'M', 77, 178, 'Kurt', 7, 0484360331, 9, 1);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (89012345, 'V', 62, 168, 'Sara', 8, 0484360332, 10, 1);
INSERT INTO "spelers" (spelerID, geslacht, gewicht, lengte, naam, ranking, telefoonnummer, datumID, tennisclubID) VALUES (90123456, 'M', 85, 193, 'Bram', 9, 0484360333, 11, 1);

--Speler emails
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('vince.driesen@gmail.com', 12345678);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('thibo.debelie@gmail.com', 98765432);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('daan.hollands@gmail.com', 97865342);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('anna.janssens@gmail.com', 23456789);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('tom.peeters@gmail.com', 34567890);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('lies.verhaegen@gmail.com', 45678901);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('jan.vanleeuw@gmail.com', 56789012);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('eva.vandamme@gmail.com', 67890123);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('kurt.maes@gmail.com', 78901234);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('sara.vermeulen@gmail.com', 89012345);
INSERT INTO "emailAdressen" (email, spelerID) VALUES ('bram.deboeck@gmail.com', 90123456);

--Toernooi datums
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (12, 1, 2024, 5, null, null);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (13, 28, 2024, 5, null, null);
--Toernooien
INSERT INTO "wedstrijdleider" (wedstrijdleiderID) VALUES (90123456);
INSERT INTO "toernooien" (toernooiID, beginDatumID, clubOrganisatorID, eindDatumID, wedstrijdLeider) VALUES (1,12, 1, 13 , 90123456);

INSERT INTO reeksen (reeksID, niveau, reeks) VALUES (1, 10, 'MANNEN');
INSERT INTO reeksen (reeksID, niveau, reeks) VALUES (2, 10, 'VROUWEN');
INSERT INTO reeksen (reeksID, niveau, reeks) VALUES (3, 10, 'KINDEREN');
INSERT INTO reeksen (reeksID, niveau, reeks) VALUES (4, 10, 'THEY');

INSERT INTO velden (veldID, vledsoort, tennisclubID) VALUES (1, 'DIRT', 1);
INSERT INTO velden (veldID, vledsoort, tennisclubID) VALUES (2, 'GRAVEL', 1);

INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (14, 3, 2024, 5, 30, 5);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (15, 4, 2024, 5, 30, 6);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (16, 5, 2024, 5, 30, 5);
INSERT INTO "datums" (datumID, dag, jaar, maand, minuten, uur) VALUES (17, 6, 2024, 5, 30, 6);

INSERT INTO "sheidsen" (arbiterRanking, scheidsID) VALUES ('nope', 89012345);

INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (1, 1, null, null, null,  14, 1, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (2, 1, null, null, null,  14, 1, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (3, 1, null, null, null,  15, 1, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (4, 1, null, null, null,  15, 1, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (5, 2, null, null, null,  16, 1, 1, 2);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (6, 2, null, null, null,  16, 1, 1, 2);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (7, 3, null, null, null,  17, 1, 1, 1);
INSERT INTO Finales (matchID, scheidsID) VALUES (7, 89012345);

INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (8, 1, null, null, null,  14, 2, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (9, 1, null, null, null,  14, 2, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (10, 1, null, null, null,  15, 2, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (11, 1, null, null, null,  15, 2, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (12, 2, null, null, null,  16, 2, 1, 2);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (13, 2, null, null, null,  16, 2, 1, 2);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (14, 3, null, null, null,  17, 2, 1, 1);
INSERT INTO Finales (matchID, scheidsID) VALUES (14, 89012345);

INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (15, 1, null, null, null,  14, 3, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (16, 1, null, null, null,  14, 3, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (17, 1, null, null, null,  15, 3, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (18, 1, null, null, null,  15, 3, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (19, 2, null, null, null,  16, 3, 1, 2);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (20, 2, null, null, null,  16, 3, 1, 2);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (21, 3, null, null, null,  17, 3, 1, 1);
INSERT INTO Finales (matchID, scheidsID) VALUES (21, 89012345);

INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (22, 1, null, null, null,  14, 4, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (23, 1, null, null, null,  14, 4, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (24, 1, null, null, null,  15, 4, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (25, 1, null, null, null,  15, 4, 1, 1);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (26, 2, null, null, null,  16, 4, 1, 2);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (27, 2, null, null, null,  16, 4, 1, 2);
INSERT INTO matchen (matchID, matchRonde, scorethuis, scoreuit, uitslag, datumID, reeksID, toernooiID, veldID) VALUES (28, 3, null, null, null,  17, 4, 1, 1);
INSERT INTO Finales (matchID, scheidsID) VALUES (28, 89012345);






