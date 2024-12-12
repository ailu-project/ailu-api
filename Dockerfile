FROM python:3.13

WORKDIR /usr/src/app

ENV PYTHONDONTWRITEBYTECODE 1

ENV PYTHONUNBUFFERED 1

RUN apt-get update \
    && apt-get install -y libpq-dev gcc python3-dev
RUN pip install --upgrade pip

COPY ./requirements.txt .

RUN pip install -r requirements.txt

RUN apt-get install -y wget openssl build-essential libssl-dev libxrender-dev git-core libx11-dev libxext-dev libfontconfig1-dev libfreetype6-dev fontconfig xfonts-base xfonts-75dpi

RUN wget https://github.com/wkhtmltopdf/packaging/releases/download/0.12.6.1-3/wkhtmltox_0.12.6.1-3.bookworm_amd64.deb

RUN dpkg -i wkhtmltox_0.12.6.1-3.bookworm_amd64.deb

RUN apt-get install -f -y

COPY ./entrypoint.sh /usr/src/app/entrypoint.sh
RUN ls -l /usr/src/app/entrypoint.sh  # Verifica se o arquivo foi copiado
RUN chmod +x /usr/src/app/entrypoint.sh


COPY . .

ENTRYPOINT ["/usr/src/app/entrypoint.sh"]

