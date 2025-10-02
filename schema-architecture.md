This Spring Boot application uses both MVC and REST controllers. Thymeleaf templates are used for the admin Doctor dashboards, while REST APIs serve all other modules. The application interacts with two databases-MySQL (for patient, doctor, appointment, and admin data) and MongoDB (for prescriptions). All controllers route requests through a common service layer, which in turn delegates to the approporite repositories. MySQL uses JPA entities while MongoDB uses document models. 

1. The user opens the AdminDashboard or Appointment page, or uses an app.
2. The request goes to the right controller (Thymeleaf for web pages, REST for APIs).
3. The controller asks the service layer to handle the logic.
4. The service layer uses the correct repository to get or save data.
5. The repository talks to MySQL (for patients, doctors, appointments, admins) or MongoDB (for prescriptions).
6. Data from the database is turned into Java model objects (JPA entities for MySQL, document models for MongoDB).
7. The models are then used to create an HTML page (for dashboards) or sent as JSON (for APIs).
