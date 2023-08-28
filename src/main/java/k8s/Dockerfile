from ubuntu:22.04 aS jammy
# TODO
ARG PASSWORD
# TODO another one
# Noncompliant
RUN wget --user=guest --password="$PASSWORD" https://example2.com
RUN ssh-keygen -N $EMPTY -t dsa -b 1024 -f rsync-key
// TODO another vulnerability
RUN wget --user=guest --password="$PASSWORD2" https://example3.com