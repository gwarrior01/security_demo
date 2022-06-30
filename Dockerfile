FROM postgres:alpine
ENV POSTGRES_PASSWORD homer_forever
ENV POSTGRES_DB security
EXPOSE 5432