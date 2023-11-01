import axios from 'axios'
import { config } from '../../Constants'

export const webScrapApi = {
  authenticate,
  signup,
  numberOfUsers,
  numberOfLinks,
  getUsers,
  deleteUser,
  getLinksByUser,
  getLinks,
  addLink,
  deleteLink
}

function authenticate(username, password) {
  return instance.post('/auth/authenticate', { username, password }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function signup(user) {
  return instance.post('/auth/signup', user, {
    headers: { 'Content-type': 'application/json' }
  })
}

function numberOfUsers() {
  return instance.get('/public/numberOfUsers')
}

function numberOfLinks() {
  return instance.get('/public/numberOfLinks')
}

function getUsers(user, username) {
  const url = username ? `/api/users/${username}` : '/api/users'
  return instance.get(url, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function deleteUser(user, username) {
  return instance.delete(`/api/users/${username}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function getLinksByUser(user) {
  const url = '/api/links/user'
  return instance.get(url, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function getLinks(user, text) {
  const url = text ? `/api/links?text=${text}` : `/api/links`
  return instance.get(url, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function deleteLink(user, id) {
  return instance.delete(`/api/links/${id}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function addLink(user, link) {
  return instance.post('/api/links', link, {
    headers: {
      'Content-type': 'application/json',
      'Authorization': basicAuth(user)
    }
  })
}

// -- Axios

const instance = axios.create({
  baseURL: config.url.API_BASE_URL
})

// -- Helper functions

function basicAuth(user) {
  return `Basic ${user.authdata}`
}