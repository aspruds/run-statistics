# Run Statistics

:zap: TODO: Remove blocking (await()) calls from services.statistics.db services.

This application collects statistics from various data providers. Currently, skriesim.lv is supported, but there are plans to add parsers for noskrien.lv and sportlat.lv. Data is retrieved as HTML, parsed into data objects (athletes, coaches, clubs, races & race results, countries, age-groups, discipline types). Collected data is then exported to a generic format (Athlete from skriesim.lv-> Person with skriesim.lv id), and finally, loaded into SQL database.
