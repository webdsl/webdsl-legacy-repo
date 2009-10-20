AC_DEFUN([XT_ENABLE_JAVA_BACKEND], [
  AC_ARG_ENABLE([java-backend],
    [AS_HELP_STRING([--enable-java-backend], [Enable Java backend for WebDSL calls during build @<:@default=no@:>@])],
    [USE_JAVA_BACKEND="$enableval"])
  AM_CONDITIONAL([XT_ENABLE_JAVA_BACKEND], [test "$USE_JAVA_BACKEND" = "yes"])
])

AC_DEFUN([XT_USE_STRC_JAVA], [

  if test "$USE_JAVA_BACKEND" = "yes"; then
    XT_CHECK_PACKAGE([STRC_JAVA],[strc-java])
    WEBDSLC="java -cp $STRC_JAVA/share/strc-java/strategoxt.jar -jar $prefix/bin/webdsl.jar"

    AC_MSG_CHECKING([for strategoxt.jar at $STRC_JAVA/share/strc-java/strategoxt.jar])
    test -f $STRC_JAVA/share/strc-java/strategoxt.jar
    if test $? -eq 0; then
      AC_MSG_RESULT([yes])
    else
      AC_MSG_RESULT([no])
      AC_MSG_ERROR([cannot find strategoxt.jar. Is strc-java installed correctly?])
    fi
  fi
])
