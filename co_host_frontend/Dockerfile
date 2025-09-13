# Use official Node.js runtime as a parent image
FROM node:18-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies (including dev dependencies for build)
RUN npm ci

# Copy the source code
COPY . .

# Build the application
RUN npm run build

# Use Nginx to serve the built application
FROM nginx:alpine

# Copy the built application from the previous stage
COPY --from=0 /app/dist /usr/share/nginx/html

# Copy custom nginx configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]