# Use Node.js base image
FROM node:18
#RUN mkdir /app/football-standings
# Set working directory
WORKDIR /app/football-standings

# Install dependencies
COPY football-standings /app/football-standings
ARG REACT_APP_BACKEND_URL
ENV REACT_APP_BACKEND_URL=$REACT_APP_BACKEND_URL
RUN npm ci

# Install dependencies with correct permissions
RUN npm ci --unsafe-perm

# Build the React app
RUN npm install
RUN npm run build
# Install 'serve' to serve the build folder
#RUN npm install -g serve

# Expose port (default port for 'serve' is 3000)
EXPOSE 3000

# Command to run the app
CMD ["npm","start"]

