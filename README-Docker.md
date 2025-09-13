# Cohost Application - Docker Setup

This document explains how to run the Cohost application using Docker and Docker Compose.

## Prerequisites

- Docker Desktop or Docker Engine
- Docker Compose
- Git

## Architecture

The application consists of three main services:
- **Frontend**: React application served by Nginx
- **Backend**: Spring Boot application with REST API
- **Database**: MySQL 8.0 database

## Quick Start

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Cohost
   ```

2. **Run the entire application**
   ```bash
   docker-compose up -d
   ```

3. **Access the application**
   - Frontend: http://localhost
   - Backend API: http://localhost:8080/api
   - Database: localhost:3307

## Development Setup

For development, you can use the development compose file which enables debugging and hot reloading:

```bash
# Run only the database for local development
docker-compose -f docker-compose.dev.yml up database -d

# Then run backend and frontend locally for development
```

## Environment Variables

The application supports the following environment variables:

### Backend (Spring Boot)
- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `SERVER_PORT`: Server port (default: 8080)
- `JWT_SECRET`: JWT signing secret
- `JWT_EXPIRATION`: JWT token expiration time in milliseconds

### Database (MySQL)
- `MYSQL_ROOT_PASSWORD`: Root password for MySQL
- `MYSQL_DATABASE`: Database name
- `MYSQL_USER`: Application database user
- `MYSQL_PASSWORD`: Application database password

## Commands

### Build and run all services
```bash
docker-compose up --build
```

### Run in detached mode
```bash
docker-compose up -d
```

### View logs
```bash
docker-compose logs -f
docker-compose logs -f backend  # Backend logs only
docker-compose logs -f frontend # Frontend logs only
```

### Stop all services
```bash
docker-compose down
```

### Stop and remove volumes (will delete database data)
```bash
docker-compose down -v
```

### Rebuild specific service
```bash
docker-compose build backend
docker-compose up -d backend
```

## Database Access

To access the MySQL database directly:

```bash
# Connect to database container
docker-compose exec database mysql -u cohost_user -p cohost_db

# Or use external MySQL client
mysql -h localhost -P 3307 -u cohost_user -p cohost_db
```

## Health Checks

The backend includes health check endpoints:
- Health status: http://localhost:8080/actuator/health

## Troubleshooting

### Port conflicts
If you get port conflicts, modify the ports in `docker-compose.yml`:
```yaml
ports:
  - "8081:8080"  # Change left side to different port
```

### Database connection issues
1. Ensure the database container is fully started before backend
2. Check database logs: `docker-compose logs database`
3. Verify database credentials in environment variables

### Frontend API connection
The frontend is configured to proxy API calls to the backend. If you encounter CORS issues:
1. Check the nginx.conf proxy configuration
2. Verify CORS settings in backend application.properties

### Clean restart
If you encounter persistent issues:
```bash
docker-compose down -v
docker system prune -f
docker-compose up --build
```

## Production Deployment

For production deployment:

1. **Create environment-specific docker-compose.prod.yml**
2. **Set secure environment variables**
3. **Use proper secrets management**
4. **Configure proper reverse proxy (nginx/apache)**
5. **Set up SSL/TLS certificates**
6. **Configure proper logging and monitoring**

Example production environment variables:
```bash
# Use secure passwords and secrets
MYSQL_ROOT_PASSWORD=secure_root_password
MYSQL_PASSWORD=secure_app_password
JWT_SECRET=your_secure_jwt_secret_key
SPRING_JPA_HIBERNATE_DDL_AUTO=validate
SPRING_JPA_SHOW_SQL=false
```

## Features Included

### RSVP System
- ✅ Create, update, and delete RSVPs
- ✅ Multiple RSVP statuses (Attending, Maybe, Not Attending)
- ✅ Real-time RSVP counts display
- ✅ User-specific RSVP status tracking

### Authentication
- ✅ JWT-based authentication
- ✅ User registration and login
- ✅ Protected API endpoints

### Event Management
- ✅ Create, view, and manage events
- ✅ Community-based event organization
- ✅ Event details with location and date

### Community Features
- ✅ Community creation and management
- ✅ User profiles and management

## API Endpoints

### RSVP Endpoints
- `POST /api/events/{eventId}/rsvp` - Create/Update RSVP
- `GET /api/events/{eventId}/rsvp` - Get all RSVPs for event
- `GET /api/events/{eventId}/rsvp/my` - Get current user's RSVP
- `PUT /api/rsvp/{rsvpId}` - Update RSVP
- `DELETE /api/rsvp/{rsvpId}` - Delete RSVP

### Other Endpoints
- Authentication: `/api/auth/*`
- Events: `/api/events/*`
- Communities: `/api/communities/*`
- Users: `/api/users/*`