#!/bin/bash

# Start the MongoDB server
exec mongod --dbpath /data/db --nojournal --auth --bind_ip_all

# Create a file to store the MongoDB shell connection information
touch /home/mongodb/.dbshell

# Set permissions on the file
chmod 666 qaz2:/home/mongodb/.dbshell

# Connect to the MongoDB instance and run an initialization script
mongo qaz2:/init-mongo4.js:ro
