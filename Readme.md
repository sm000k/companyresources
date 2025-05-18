# Company Resources Application
## Author: Paweł Łącki

## Wymagania
- Java 17
- Maven 3.8+

## Jak uruchomić projekt?
1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/your-repo-url.git
   cd companyresources
   ```
2. W katalogu resources/static znajduje się statyczna skompilowana postać frontendu, która uruchomi się po skompilowaniu backendu.

3. Uruchom backend:
   Z katalogu głównego (`companyresources`):
   ```bash
   ./gradlew bootRun
   ```
   lub na systemie Windows:
   ```cmd
   gradlew bootRun
   ```
   Aplikacja wystartuje pod adresem [http://localhost:8080](http://localhost:8080).

4. Uruchom testy:
   Aby uruchomić wszystkie testy:
   ```bash
   ./gradlew test
   ```

5. Uzyskaj dostęp do konsoli bazy danych H2:
   Odwiedź [http://localhost:8080/h2-ui](http://localhost:8080/h2-ui)  
   JDBC URL: `jdbc:h2:mem:itresources`  
   Nazwa użytkownika: `sa`  
   Hasło: `password`

---

## Rozwiązywanie problemów

- Upewnij się, że używasz Java 17 lub nowszej (`java -version`).
- Jeśli zmieniasz frontend, zbuduj go ponownie przed uruchomieniem backendu.
- Jeśli otrzymasz błędy dotyczące brakujących beanów w testach, upewnij się, że używasz `@Mock` i `@InjectMocks` zamiast przestarzałego `@MockBean`.