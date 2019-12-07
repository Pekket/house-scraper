FROM nginx:1.13

COPY ./nginx/conf.d /etc/nginx/conf.d
COPY react /var/www

CMD nginx -g 'daemon off;'
