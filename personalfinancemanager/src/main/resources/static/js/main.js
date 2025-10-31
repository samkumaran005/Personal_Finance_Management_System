// helper fetch with credentials so session cookie is included
async function apiFetch(url, method='GET', body=null) {
  const opts = {
    method,
    credentials: 'include', // important: include session cookie
    headers: { 'Content-Type': 'application/json' }
  };
  if (body) opts.body = JSON.stringify(body);
  const resp = await fetch(url, opts);
  const data = await resp.json().catch(()=>null);
  return { status: resp.status, data };
}
