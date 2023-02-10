FROM node:18.14.0
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build
EXPOSE 4200
CMD ["npm", "start"]
