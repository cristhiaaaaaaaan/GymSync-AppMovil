# GymSync - Tu Compañero de Gimnasio


- **Nombre**: Cristhian Aguilar Cerna
- **Curso**: [ I, III-25, P-02]
- **Profesor**: Ever Barahona Mendoza

### Descripción del Proyecto
GymSync es una aplicación para Android pensada para el cliente del gimnasio. El objetivo es que el usuario pueda consultar su rutina diaria, registrar sus avances, ver su progreso en fotos y gestionar su membresía, todo desde el celular.

### Funcionalidades del Usuario

**Requisitos Técnicos:**
- **CRUD:** El usuario podrá gestionar su galería de fotos de progreso, añadiendo nuevas fotos (Crear), viéndolas (Leer), actualizando notas (Actualizar) y eliminando fotos antiguas (Borrar).
- **Listas y Búsqueda:** Se mostrará una lista de los ejercicios de la rutina del día.
- **Cámara:** La función principal será tomar fotos para la galería de progreso personal.
- **Galería:** Permitirá al usuario subir una foto de perfil desde su galería.
- **Diálogos:** Se usarán para confirmar acciones como "Marcar entrenamiento como completado" o realizar el pago de la membresía.

**Funciones Clave de la App:**
- **Mi Plan de Hoy:** Muestra la rutina de ejercicios asignada para el día actual.
- **Registro de Avance:** Permite al usuario anotar el peso o las repeticiones que hizo en cada ejercicio.
- **Mi Progreso Fotográfico:** Una galería personal para que el usuario suba sus fotos y vea su evolución física.
- **Mi Perfil y Pagos:** Muestra el estado de la membresía del usuario y le permite realizar el pago.
- **Rachas y Logros:** Notificaciones que motivan al usuario por su consistencia y esfuerzo.

### Mockups 

- **Pantalla Principal ("Mi Plan de Hoy"):**
<img src="mockups/pantalla_principal.jpg" alt="Diseño de la pantalla principal" width="250"/>

- **Pantalla de Perfil y Pagos:**
 <img src="mockups/perfil_pagos.jpg" alt="Diseño de la pantalla principal" width="250"/>

- **Galería de Progreso Fotográfico:**
<img src="mockups/Galería_de_Progreso.jpg" alt="Diseño de la pantalla principal" width="250"/>

---

## API Documentation

### API URL

Production: `https://gymsync-api.onrender.com`

Local: `http://localhost:3000`

### Response Format

Success:
```json
{
  "data": { ... },
  "responseCode": 200,
  "message": "Success message"
}
```

Error:
```json
{
  "responseCode": 400,
  "message": "Error message"
}
```

### GET /
API information.

Response:
```json
{
  "message": "GymSync API - Mobile App Backend",
  "version": "2.1.0",
  "endpoints": {
    "users": "/users",
    "ejercicios": "/ejercicios",
    "rutinas": "/rutinas",
    "membresias": "/membresias"
  },
  "documentation": "See README.md for full API documentation"
}
```

---

## 1. Users Endpoints (Authentication)

### GET /users
Get all users.

**Response:**
```json
{
  "data": [
    {
      "id": "user123",
      "name": "Juan Pérez",
      "email": "juan@example.com",
      "fotoPerfil": "",
      "fechaRegistro": "2025-12-09T10:00:00.000Z"
    }
  ],
  "responseCode": 200,
  "message": "Users retrieved successfully"
}
```

### GET /users/:id
Get a specific user by ID.

**Parameters:**
- `id` (string) - User ID

**Response:**
```json
{
  "data": {
    "id": "user123",
    "name": "Juan Pérez",
    "email": "juan@example.com",
    "fotoPerfil": "",
    "fechaRegistro": "2025-12-09T10:00:00.000Z"
  },
  "responseCode": 200,
  "message": "User retrieved successfully"
}
```

### GET /users/email/:email
Get a user by email (useful for login).

**Parameters:**
- `email` (string) - User email

**Response:**
```json
{
  "data": {
    "id": "user123",
    "name": "Juan Pérez",
    "email": "juan@example.com",
    "fotoPerfil": "",
    "fechaRegistro": "2025-12-09T10:00:00.000Z"
  },
  "responseCode": 200,
  "message": "User retrieved successfully"
}
```

### POST /users
Create a new user (Register).

**Request Body:**
```json
{
  "id": "user456",
  "name": "María García",
  "email": "maria@example.com",
  "fotoPerfil": "",
  "fechaRegistro": "2025-12-09T10:00:00.000Z"
}
```

**Response (Success):**
```json
{
  "data": {
    "id": "user456",
    "name": "María García",
    "email": "maria@example.com",
    "fotoPerfil": "",
    "fechaRegistro": "2025-12-09T10:00:00.000Z"
  },
  "responseCode": 201,
  "message": "User created successfully"
}
```

**Response (Email already exists):**
```json
{
  "responseCode": 400,
  "message": "Email already registered"
}
```

### PUT /users/:id
Update an existing user.

**Parameters:**
- `id` (string) - User ID

**Request Body (all fields optional):**
```json
{
  "name": "María García López",
  "fotoPerfil": "https://example.com/photo.jpg"
}
```

**Response:**
```json
{
  "data": {
    "id": "user456",
    "name": "María García López",
    "email": "maria@example.com",
    "fotoPerfil": "https://example.com/photo.jpg",
    "fechaRegistro": "2025-12-09T10:00:00.000Z"
  },
  "responseCode": 200,
  "message": "User updated successfully"
}
```

### DELETE /users/:id
Delete a user.

**Parameters:**
- `id` (string) - User ID

**Response:**
```json
{
  "data": {
    "id": "user456",
    "name": "María García López",
    "email": "maria@example.com",
    "fotoPerfil": "https://example.com/photo.jpg",
    "fechaRegistro": "2025-12-09T10:00:00.000Z"
  },
  "responseCode": 200,
  "message": "User deleted successfully"
}
```

---

## 2. Ejercicios Endpoints

### GET /ejercicios
Get all exercises.

**Response:**
```json
{
  "data": [
    {
      "id": "1",
      "nombre": "Press de Banca",
      "series": 4,
      "repeticiones": 10,
      "pesoRecomendado": 60,
      "notas": "Mantener espalda pegada al banco"
    }
  ],
  "responseCode": 200,
  "message": "Ejercicios retrieved successfully"
}
```

### GET /ejercicios/:id
Get a specific exercise by ID.

**Parameters:**
- `id` (string) - Exercise ID

**Response:**
```json
{
  "data": {
    "id": "1",
    "nombre": "Press de Banca",
    "series": 4,
    "repeticiones": 10,
    "pesoRecomendado": 60,
    "notas": "Mantener espalda pegada al banco"
  },
  "responseCode": 200,
  "message": "Ejercicio retrieved successfully"
}
```

### POST /ejercicios
Create a new exercise.

**Request Body:**
```json
{
  "id": "4",
  "nombre": "Curl de Biceps",
  "series": 3,
  "repeticiones": 12,
  "pesoRecomendado": 15,
  "notas": "Evitar balancear el cuerpo"
}
```

**Response:**
```json
{
  "data": {
    "id": "4",
    "nombre": "Curl de Biceps",
    "series": 3,
    "repeticiones": 12,
    "pesoRecomendado": 15,
    "notas": "Evitar balancear el cuerpo"
  },
  "responseCode": 201,
  "message": "Ejercicio created successfully"
}
```

### PUT /ejercicios/:id
Update an existing exercise.

**Parameters:**
- `id` (string) - Exercise ID

**Request Body (all fields optional):**
```json
{
  "series": 4,
  "repeticiones": 15,
  "pesoRecomendado": 20
}
```

**Response:**
```json
{
  "data": {
    "id": "4",
    "nombre": "Curl de Biceps",
    "series": 4,
    "repeticiones": 15,
    "pesoRecomendado": 20,
    "notas": "Evitar balancear el cuerpo"
  },
  "responseCode": 200,
  "message": "Ejercicio updated successfully"
}
```

### DELETE /ejercicios/:id
Delete an exercise.

**Parameters:**
- `id` (string) - Exercise ID

**Response:**
```json
{
  "data": {
    "id": "4",
    "nombre": "Curl de Biceps",
    "series": 4,
    "repeticiones": 15,
    "pesoRecomendado": 20,
    "notas": "Evitar balancear el cuerpo"
  },
  "responseCode": 200,
  "message": "Ejercicio deleted successfully"
}
```

---

## 3. Rutinas Endpoints

### GET /rutinas
Get all workout routines.

**Response:**
```json
{
  "data": [
    {
      "id": "1",
      "usuarioId": "user1",
      "fecha": "2025-12-04T04:35:05.055Z",
      "nombre": "Rutina Pecho y Triceps",
      "ejercicios": ["1", "2"],
      "completada": false
    }
  ],
  "responseCode": 200,
  "message": "Rutinas retrieved successfully"
}
```

### GET /rutinas/:id
Get a specific routine by ID.

**Parameters:**
- `id` (string) - Routine ID

**Response:**
```json
{
  "data": {
    "id": "1",
    "usuarioId": "user1",
    "fecha": "2025-12-04T04:35:05.055Z",
    "nombre": "Rutina Pecho y Triceps",
    "ejercicios": ["1", "2"],
    "completada": false
  },
  "responseCode": 200,
  "message": "Rutina retrieved successfully"
}
```

### POST /rutinas
Create a new routine.

**Request Body:**
```json
{
  "id": "3",
  "usuarioId": "user2",
  "fecha": "2025-12-04T10:00:00.000Z",
  "nombre": "Rutina Espalda",
  "ejercicios": ["3"],
  "completada": false
}
```

**Response:**
```json
{
  "data": {
    "id": "3",
    "usuarioId": "user2",
    "fecha": "2025-12-04T10:00:00.000Z",
    "nombre": "Rutina Espalda",
    "ejercicios": ["3"],
    "completada": false
  },
  "responseCode": 201,
  "message": "Rutina created successfully"
}
```

### PUT /rutinas/:id
Update an existing routine.

**Parameters:**
- `id` (string) - Routine ID

**Request Body (all fields optional):**
```json
{
  "completada": true,
  "nombre": "Rutina Espalda y Biceps"
}
```

**Response:**
```json
{
  "data": {
    "id": "3",
    "usuarioId": "user2",
    "fecha": "2025-12-04T10:00:00.000Z",
    "nombre": "Rutina Espalda y Biceps",
    "ejercicios": ["3"],
    "completada": true
  },
  "responseCode": 200,
  "message": "Rutina updated successfully"
}
```

### DELETE /rutinas/:id
Delete a routine.

**Parameters:**
- `id` (string) - Routine ID

**Response:**
```json
{
  "data": {
    "id": "3",
    "usuarioId": "user2",
    "fecha": "2025-12-04T10:00:00.000Z",
    "nombre": "Rutina Espalda y Biceps",
    "ejercicios": ["3"],
    "completada": true
  },
  "responseCode": 200,
  "message": "Rutina deleted successfully"
}
```

---

## 4. Membresias Endpoints

### GET /membresias
Get all memberships.

**Response:**
```json
{
  "data": [
    {
      "id": "1",
      "usuarioId": "user1",
      "tipo": "Premium",
      "fechaInicio": "2025-12-04T04:35:05.060Z",
      "fechaVencimiento": "2026-01-04T04:35:05.060Z",
      "activa": true,
      "monto": 50
    }
  ],
  "responseCode": 200,
  "message": "Membresias retrieved successfully"
}
```

### GET /membresias/:id
Get a specific membership by ID.

**Parameters:**
- `id` (string) - Membership ID

**Response:**
```json
{
  "data": {
    "id": "1",
    "usuarioId": "user1",
    "tipo": "Premium",
    "fechaInicio": "2025-12-04T04:35:05.060Z",
    "fechaVencimiento": "2026-01-04T04:35:05.060Z",
    "activa": true,
    "monto": 50
  },
  "responseCode": 200,
  "message": "Membresia retrieved successfully"
}
```

### POST /membresias
Create a new membership.

**Request Body:**
```json
{
  "id": "3",
  "usuarioId": "user3",
  "tipo": "VIP",
  "fechaInicio": "2025-12-04T10:00:00.000Z",
  "fechaVencimiento": "2026-03-04T10:00:00.000Z",
  "activa": true,
  "monto": 100
}
```

**Response:**
```json
{
  "data": {
    "id": "3",
    "usuarioId": "user3",
    "tipo": "VIP",
    "fechaInicio": "2025-12-04T10:00:00.000Z",
    "fechaVencimiento": "2026-03-04T10:00:00.000Z",
    "activa": true,
    "monto": 100
  },
  "responseCode": 201,
  "message": "Membresia created successfully"
}
```

### PUT /membresias/:id
Update an existing membership.

**Parameters:**
- `id` (string) - Membership ID

**Request Body (all fields optional):**
```json
{
  "activa": false,
  "fechaVencimiento": "2026-06-04T10:00:00.000Z"
}
```

**Response:**
```json
{
  "data": {
    "id": "3",
    "usuarioId": "user3",
    "tipo": "VIP",
    "fechaInicio": "2025-12-04T10:00:00.000Z",
    "fechaVencimiento": "2026-06-04T10:00:00.000Z",
    "activa": false,
    "monto": 100
  },
  "responseCode": 200,
  "message": "Membresia updated successfully"
}
```

### DELETE /membresias/:id
Delete a membership.

**Parameters:**
- `id` (string) - Membership ID

**Response:**
```json
{
  "data": {
    "id": "3",
    "usuarioId": "user3",
    "tipo": "VIP",
    "fechaInicio": "2025-12-04T10:00:00.000Z",
    "fechaVencimiento": "2026-06-04T10:00:00.000Z",
    "activa": false,
    "monto": 100
  },
  "responseCode": 200,
  "message": "Membresia deleted successfully"
}
```

---

## HTTP Status Codes

- `200` - Success
- `201` - Created
- `400` - Bad Request
- `404` - Not Found
- `500` - Internal Server Error


