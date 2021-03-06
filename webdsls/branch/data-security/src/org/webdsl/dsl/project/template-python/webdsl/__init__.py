import utils
import db
import querylist
import markdown
import re
import session
import polymodel
import captcha

RE_LINKS = re.compile(r'(\[\[(\w+)(\(([^\)]*)\))?(\|([^\]]+))?\]\])')
RE_TAGS = re.compile(r'<[^>]+>')

def parse_wikitext(str):
    links = RE_LINKS.findall(str)
    for (whole, page, dummy, arg, dummy2, title) in links:
        url = ''
        if page == 'home':
            page = ''
        if arg:
            url = '/%s/%s' % (page, arg)
        else:
            url = '/%s' % page
        if not title:
            title = page
        str = str.replace(whole, '[%s](%s)' % (title, url))
    return markdown.Markdown(safe_mode='escape').convert(str)

def parse_text(str):
    return markdown.Markdown(safe_mode='escape').convert(str)

def remove_tags(str):
    return RE_TAGS.sub('', str)
