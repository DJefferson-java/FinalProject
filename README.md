# 🛼 skate_tracker

**skate_tracker** is a Spring Boot–based REST API for managing skater data — including skaters, events, and locations.  
It provides CRUD (Create, Read, Update, Delete) operations for all core entities: **Skater**, **Location**, **Event**, and **SkaterEvent**.

Skate Tracker is a comprehensive digital platform designed to allow skating business owners detailed insights into skate events and the skaters who attend them. With the growing popularity of community-based skate events and the need for smarter business decisions, Skate Tracker will offer a centralized, user-friendly database to log, analyze, and optimize event performance and customer engagement.  
---

## 🚀 Features

- 🧍 Manage skater profiles (name, age)
- 📍 Manage skate locations
- 🗓️ Manage skate events (date, time, name of the event)
- 🔗 Associate skaters with events through the `SkaterEvent` entity
- 🔄 Full CRUD support via RESTful endpoints
- 🧩 Built with Spring Boot and JPA/Hibernate

## ⚙️ Technologies Used

- **Java 17+**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **Hibernate**
- **H2 / MySQL** (configurable)
- **Maven**

---

## 🧩 Entity Overview

| Entity | Description | Relationships |

|--------|--------------|----------------|

| **Skater** | Represents an individual skater. | One-to-Many with `SkaterEvent` |

| **Location** | Represents a skate location. | One-to-Many with `Event` |

| **Event** | Represents a skate event (event name, date how long). | Many-to-One with `Location`; One-to-Many with `SkaterEvent` |

| **SkaterEvent** | Join entity linking `Skater` and `Event`. | Many-to-One with both `Skater` and `Event` |

---

## 🧩 REST Endpoints

### Skater
| Method | Endpoint | Description |

|--------|-----------|-------------|

| `GET` | `/skate_tracker/skaters` | Get all skaters |

| `POST` | `/skate_tracker/addSkater` | Create a new skater |

| `PUT` | `/skate_tracker/updateSkater/{skaterId}}` | Update a skater |


### Location
| Method | Endpoint | Description |

|--------|-----------|-------------|

| `GET` | `/skate_tracker/locations` | Get all locations |

| `POST` | `/skate_tracker/addLocation` | Create a new location |

| `PUT` | `/skate_tracker/updateLocation/{locationId}` | Update a location |

| `DELETE` | `/skate_tracker/deleteLocation/1{locaitonId}` | Delete a location and any events associated |


### Event
| Method | Endpoint | Description |

|--------|-----------|-------------|

| `POST` | `/skate_tracker/addEvent` | Create a new event |

| `PUT` | `/skate_tracker/updateEvent/{eventId}` | Update an event |

### SkaterEvent
| Method | Endpoint | Description |

|--------|-----------|-------------|

| `POST` | `/skate_tracker/addSkaterEvent?skaterId={skaterId}&eventId={eventId}` | Add/Create a skater event |

---